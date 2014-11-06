package ServiceImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import net.sf.json.JSONArray;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;

import Service.CreateIndexService;
import Service.GetService;



public class CreateIndexServiceImpl implements CreateIndexService {

	private GetService getService;
	
	public void setGetService(GetService getService) {
		this.getService = getService;
	}


	String basePath = this.getClass().getClassLoader().getResource("/")
			.getPath();
	@Override
	public void createIndex(String path) throws Exception {
		
		String path0 = basePath+path + "/Index/";
		

		// TODO Auto-generated method stub
		IndexWriter writer = null;
		try {
			File file = new File(path0);
			if (!file.exists()) {
				file.mkdirs();
				file.createNewFile();// �������ļ������ھʹ�����
			}
			else {
				String[] tempList = file.list();
				File temp = null;
				for (int i = 0; i < tempList.length; i++) {
					temp = new File(path0 + tempList[i]);
					if (temp.isFile())
						temp.delete();
				}

			}
			if (getService.getTitle(path) != "") {
				JSONArray title = JSONArray.fromObject(getService.getTitle(path));
				Directory directory = FSDirectory.open(file);
				// 2.����IndexWriter
				IndexWriterConfig iwc = new IndexWriterConfig(
						Version.LUCENE_35, new StandardAnalyzer(
								Version.LUCENE_35));

				writer = new IndexWriter(directory, iwc);
				Document doc = null;
				for (int i = 0; i < title.size(); i++) {
					doc = new Document();
					doc.add(new Field("title", title.get(i).toString(),
							Field.Store.YES, Field.Index.ANALYZED));
					System.out.println(title.get(i).toString()+"���ڱ�����");
					writer.addDocument(doc);
				}
			} 
		} catch (CorruptIndexException ce) {
			ce.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (CorruptIndexException e2) {
				e2.printStackTrace();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}

	}

	
	@Override
	public String sreach(String path,String key) {
	ArrayList<String> result=new ArrayList<String>();
		try {
			// 1.����Directory
			
			String path0 =basePath+ path + "/Index/";
			File file = new File(path0);
			if (file.list().length > 0) {
				Directory directory = FSDirectory.open(file);
				// 2.����IndexReader
				IndexReader reader = IndexReader.open(directory);
				// 3.����IndexReader����IndexSearcher
				IndexSearcher searcher = new IndexSearcher(reader);
				// 4.����������Query
				// ����parser��ȷ��Ҫ�������ļ������ݣ��ڶ���������ʾ��������

				QueryParser parser = new QueryParser(Version.LUCENE_35,
						"title", new StandardAnalyzer(Version.LUCENE_35));
				// ����query����ʾ������Ϊcontent�а���key���ĵ�
				Query query = parser.parse(key);
				// 5.����searcher����������TopDocs
				TopDocs td = searcher.search(query, null, 100);
				ScoreDoc[] hits = td.scoreDocs;
				System.out.println("��Ϊ�����ҵ�" + td.totalHits + "�����");
				System.out.println(hits.length);
				for (int i = 0; i < hits.length; i++) {
					Document document = searcher.doc(hits[i].doc);
					System.out.println(document.get("title"));
					result.add(document.get("title"));
					
					System.out.println(result.get(i));
				}

				// 9.�ر�reader
				reader.close();
			}

		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(result.size()>0)
		{
		JSONArray jsonArray = JSONArray.fromObject(result);
		return jsonArray.toString();
		}
		else
		{
			return "nothing";
		}
	}

}
