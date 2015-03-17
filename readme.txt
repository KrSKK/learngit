Git is a distributed version control system.
Git is free software.

Command with git
local
1.git init
2.git add file
3.git commit -m "description"
4.git status
5.git diff
6.git log/ git log --pretty=oneline
7.git reset --hard HEAD^
8.git reflog
9.git diff HEAD -- file
10.git checkout -- file
11.git reset HEAD file
12.git rm, git commit -m "description"

remote
1.git remote add origin git@github.com:KrSKK/learngit.git
2.git push -u origin master
3.git push origin master
4.git clone git@github.com:KrSKK/learngit.git
5.git remote/git remote -v

branch
1.git checkout -b dev
2.git branch
3.git checkout master
4.git merge dev
5.git branch -d dev
6.git log --graph
7.git merge --no-ff -m "merge with no-ff" dev
8.git stash, git stash list, git stash pop, git stash apply stash@{0}
9.git branch --set-upstream dev origin/dev, git pull, git commit -m "merge", git push origin dev

tag
1.git tag v1.0/ git tag -a v1.0 -m "description" commitid
2.git show tagname
3.git tag -d v1.0
4.git push origin tagname/ git push origin --tags
5.git tag -d v1.0, git push origin :refs/tages/v1.0
