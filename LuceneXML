package LuceneXML;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LuceneXML extends DefaultHandler {
	private StringBuffer iobuf = new StringBuffer();
	private HashMap attrmap;
	private Document doc;

	public void startDocument() throws SAXException {
		doc = new Document();
		System.out.println("XML parsing begins");
	}

	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) {
		iobuf.setLength(0);
		if (atts.getLength() > 0) {
			attrmap = new HashMap();
			for (int i = 0; i < atts.getLength(); i++)
				attrmap.put(atts.getQName(i), atts.getValue(i));
		}
	}

	public void characters(char[] chars, int start, int length)
			throws SAXException {
		iobuf.append(chars, start, length);
	}

	public void endElement(String namespaceURI, String localName,
			String fullName) throws SAXException {
		if (fullName.equals("personal-info")) {
			return;
		} else if (fullName.equals("contact")) {
			Iterator iter = attrmap.keySet().iterator();
			while (iter.hasNext()) {
				String attName = (String) iter.next();
				String attValue = (String) attrmap.get(attName);
				doc.add(new Field(attName, attValue, Field.Store.YES,
						Field.Index.ANALYZED));
			}
		} else {
			doc.add(new Field(fullName, iobuf.toString(), Field.Store.YES,
					Field.Index.ANALYZED));
		}
	}

	// parse XML files
	public Document getDoc(String fileDir) {
		try {
			SAXParserFactory sf = SAXParserFactory.newInstance();
			SAXParser sp = sf.newSAXParser();
			sp.parse(new InputSource(fileDir), this);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}

	public void endDocument() throws SAXException {
		System.out.println("XML parsing terminated");
	}

	// get file header
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	// index build
	public static void buildIndex(File dataDir, File indexDir) throws Exception {
		LuceneXML LXml = new LuceneXML();
		Directory Dir = new SimpleFSDirectory(indexDir);
		IndexWriter indexWriter = new IndexWriter(Dir, new StopAnalyzer(
				Version.LUCENE_30), true, IndexWriter.MaxFieldLength.UNLIMITED);
		File[] dataFiles = dataDir.listFiles();
		long startTime = new Date().getTime();
		for (int i = 0; i < dataFiles.length; i++) {
			FileInputStream is = new FileInputStream(
					dataFiles[i].getCanonicalPath());
			byte[] b = new byte[3];
			is.read(b, 0, b.length);
			if (dataFiles[i].isFile() && bytesToHexString(b).equals("3c3f78")) {   // get XML files
				Document document = LXml
						.getDoc(dataFiles[i].getCanonicalPath());
				document.add(new Field("path", dataFiles[i].getCanonicalPath(),
						Field.Store.YES, Field.Index.NO));
				System.out.println(document);
				indexWriter.addDocument(document);
			}
			is.close();
		}
		indexWriter.optimize();
		indexWriter.close();
		long endTime = new Date().getTime();
		System.out.println("It takes " + (endTime - startTime)
				+ " ms to create index for all the XML files in directory: "
				+ dataDir.getPath());
	}

	// single field search
	public static void FieldSearch(File indexDir, String queryStr)
			throws Exception {
		String[] queryarray = queryStr.split(":");
		Directory dir = new SimpleFSDirectory(indexDir);
		IndexSearcher searcher = new IndexSearcher(dir);
		QueryParser parser = new QueryParser(Version.LUCENE_30, queryarray[0],
				new StopAnalyzer(Version.LUCENE_30));
		Query query = parser.parse(queryarray[1]);
		TopDocs topdocs = searcher.search(query, 10);
		System.out.println("File hts: " + topdocs.totalHits);
		for (int i = 0; i < topdocs.scoreDocs.length; i++) {
			ScoreDoc sdoc = topdocs.scoreDocs[i];
			Document document = searcher.doc(sdoc.doc);
			System.out.println("File path: " + document.get("path"));
		}
		searcher.close();
	}

	// multi-fileds search
	@SuppressWarnings("static-access")
	public static void WholeSearch(File indexDir, String queryStr)
			throws Exception {
		Directory dir = new SimpleFSDirectory(indexDir);
		IndexSearcher searcher = new IndexSearcher(dir);
		StopAnalyzer Analyzer = new StopAnalyzer(Version.LUCENE_30);
		String[] fields = new String[] { "name", "address", "city", "province",
				"postalcode", "country", "telephone" };
		BooleanClause.Occur[] flags = new BooleanClause.Occur[] {
				BooleanClause.Occur.SHOULD, BooleanClause.Occur.SHOULD,
				BooleanClause.Occur.SHOULD, BooleanClause.Occur.SHOULD,
				BooleanClause.Occur.SHOULD, BooleanClause.Occur.SHOULD,
				BooleanClause.Occur.SHOULD };
		MultiFieldQueryParser parser = new MultiFieldQueryParser(
				Version.LUCENE_30, fields, Analyzer);
		Query query = parser.parse(Version.LUCENE_30, queryStr, fields, flags,
				new StopAnalyzer(Version.LUCENE_30));
		TopDocs topdocs = searcher.search(query, 10);
		System.out.println("File hits: " + topdocs.totalHits);
		for (int i = 0; i < topdocs.scoreDocs.length; i++) {
			ScoreDoc sdoc = topdocs.scoreDocs[i];
			Document document = searcher.doc(sdoc.doc);
			System.out.println("File path: " + document.get("path"));
			for (int j = 0; j < fields.length; j++) {
				String text = document.get(fields[j]);
				SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter(
						"<" + fields[j] + ">", "</" + fields[j] + ">");
				Highlighter highlighter = new Highlighter(simpleHTMLFormatter,
						new QueryScorer(query));
				highlighter.setTextFragmenter(new SimpleFragmenter(text
						.length()));
				if (text != null) {
					TokenStream tokenStream = Analyzer.tokenStream(
							document.get(fields[j]), new StringReader(text));
					String highLightText = highlighter.getBestFragment(
							tokenStream, text);
					if (highLightText != null)
						System.out.println(highLightText);
				}
			}
		}
		searcher.close();
	}

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Directory of files for indexing: ");
		File dataDir = new File(br.readLine());
		while (!dataDir.exists()) {
			System.out.println("Directory not existes, try again: ");
			dataDir = new File(br.readLine());
		}

		System.out.println("Directory to store index files: ");
		File indexDir = new File(br.readLine());
		while (!indexDir.exists()) {
			System.out.println("Directory not existes, create new? Y/N");
			if (br.readLine().equals("Y")) {
				indexDir.mkdir();
			} else {
				System.out.println("Try again: ");
				indexDir = new File(br.readLine());
			}
		}
		System.out
				.println("=============================================================================");
		System.out.println("Indexing begins.");
		buildIndex(dataDir, indexDir);
		System.out.println("Indexing terminate. ");
		System.out
				.println("=============================================================================");
		System.out.println("Searching right now? Y/N");
		if (br.readLine().equals("Y")) {
			System.out.println("Searching for a field specified? Y/N");
			if (br.readLine().equals("Y")) {
				System.out
						.println("Field searching begins. Enter [Field:Keyword]");
				System.out.println("Searching for(ENTER to terminate): ");
				String queryStr = br.readLine();
				while (queryStr.length() != 0) {
					FieldSearch(indexDir, queryStr);
					System.out.println("Searching for(ENTER to terminate): ");
					queryStr = br.readLine();
				}
			} else {
				System.out.println("Whole searching begins. Enter [Keyword]");
				System.out.println("Searching for(ENTER to terminate): ");
				String queryStr = br.readLine();
				while (queryStr.length() != 0) {
					WholeSearch(indexDir, queryStr);
					System.out.println("Searching for(ENTER to terminate): ");
					queryStr = br.readLine();
				}
			}
			System.out.println("Searching terminate.");
		}
	}
}
