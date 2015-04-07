/*
 * FuncInoti.cpp
 *
 *  Created on: Mar 18, 2015
 *      Author: kaiwen
 */

#include <iostream>
#include <fstream>
#include <unistd.h>
#include <dirent.h>
#include <sys/stat.h>
#include <sys/inotify.h>
#include <errno.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "FuncInoti.hpp"

FuncInoti::FuncInoti() {
}

FuncInoti::~FuncInoti() {
}

// get sub-directories
std::vector<char*> FuncInoti::getSubDir(char* dir) {
	std::vector<char*> dirPath;
	char* path;
	DIR* dirp;
	struct dirent* entry;
	struct stat statbuf;

	if ((dirp = opendir(dir)) == NULL) {
		std::cout << "Can't open this directory" << std::endl;
		perror("opendir");
		exit(EXIT_FAILURE);
	}

	chdir(dir);
	while ((entry = readdir(dirp)) != NULL) {
		asprintf(&path, "%s%s", dir, entry->d_name);
		lstat(entry->d_name, &statbuf);
		if (entry->d_name[0] == '.')
			continue;
		if (S_IFDIR & statbuf.st_mode) {
			char fullname[255];
			memset(fullname, 0, sizeof(fullname));
			strncat(fullname, path, sizeof(fullname));
			strncat(fullname, "/", sizeof(fullname));
			asprintf(&path, "%s/", path);
			dirPath.push_back(path);
			std::vector<char*> sub = getSubDir(fullname);
			dirPath.insert(dirPath.begin(), sub.begin(), sub.end());
		}
	}
	chdir("..");
	closedir(dirp);
	return dirPath;
	free(path);

}

int FuncInoti::init_fd() { // create the file descriptor for accessing inotify API
	int fd = inotify_init1(IN_NONBLOCK);
	if (fd == -1) {
		perror("inotify_init1");
		exit(EXIT_FAILURE);
	}
	return fd;
	close(fd);
}

int* FuncInoti::init_wd(int fd, std::vector<char*> dirpath) { // create the watch descriptor for root file
	int* wd = (int*) calloc((int) dirpath.size(), sizeof(int));	// allocate memory for watch descriptors
	if (wd == NULL) {
		perror("calloc");
		exit(EXIT_FAILURE);
	}

	for (int i = 0; i < (int) dirpath.size(); i++) {
		wd[i] = inotify_add_watch(fd, dirpath[i], IN_CREATE | IN_DELETE);
		//std::cout << wd[i] << std::endl;
		if (wd[i] == -1) {
			std::cout << stderr << "Cannot watch " << dirpath[i] << std::endl;
			perror("inotify_add_watch");
			exit(EXIT_FAILURE);
		}
	}
	return wd;
	free(wd);
}

std::vector<char*> FuncInoti::Events_Handle(int fd, int* wd,
		std::vector<char*> dirpath) {
	std::vector<char*> update;
	char buf[4096]__attribute__ ((aligned(__alignof__(struct inotify_event))));
	const struct inotify_event* event;
	int i;
	ssize_t len;
	char* ptr;
	char* filename;
	char* foldername;

	for (;;) {
		len = read(fd, buf, sizeof buf);	//read events from inotify file descriptor
		if (len == -1 && errno != EAGAIN) {
			perror("read");
			exit(EXIT_FAILURE);
		}
		if (len <= 0)
			break;
		for (ptr = buf; ptr < buf + len;	//loop over all events in the buffer
				ptr += sizeof(struct inotify_event) + event->len) {
			event = (const struct inotify_event*) ptr;
			char folder[100];
			if (event->mask & IN_ISDIR) {					//print events type
				strncpy(folder, event->name, sizeof(folder));
				strncat(folder, "/", sizeof(folder));
				if (event->len) {
					std::cout << " [Directory: ";
					for (i = 0; i < (int) dirpath.size(); i++) {
						if (wd[i] == event->wd) {
							std::cout << dirpath[i];
							break;
						}
					}
					std::cout << folder << "] -- ";
					if (event->mask & IN_CREATE) {
						std::cout << "Directory was created" << std::endl;
					}
					if (event->mask & IN_DELETE) {
						std::cout << "Directory was deleted" << std::endl;
					}
					asprintf(&foldername, "%s%s", dirpath[i], folder);
					update.push_back(foldername);
				}
			} else if (!(event->mask & IN_IGNORED)){// if update of file, push_back file path when DICOM
				if (event->len) {
					std::cout << " [File: ";
					for (i = 0; i < (int) dirpath.size(); i++) {
						if (wd[i] == event->wd) {
							std::cout << dirpath[i];
							break;
						}
					}
					std::cout << event->name << "] -- ";
					asprintf(&filename, "%s%s", dirpath[i], event->name);
					if (event->mask & IN_CREATE) {
						std::cout << "File was created" << std::endl;
						/*std::ifstream fin;
						 fin.open(filename);
						 for (int i = 0; i < 128; i++) {
						 char x;
						 fin.read(reinterpret_cast<char*>(&x), sizeof(char));
						 }
						 std::string s;
						 char c;
						 for (int i = 0; i < 4; i++) {
						 fin.read(reinterpret_cast<char*>(&c), sizeof(char));
						 s += c;
						 }
						 if (s == "DICM") {
						 update.push_back(filename);
						 std::cout << filename << "  update" << std::endl;
						 } else {
						 std::cout << "Not Dicom file, ignore" << std::endl;
						 }
						 fin.close();*/
					}
					if (event->mask & IN_DELETE) {
						std::cout << "File was deleted" << std::endl;
					}
					update.push_back(filename);
				}
			}
		}
	}
	return update;
	free(filename);
	free(foldername);
}

