import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Explanation;
import org.apache.lucene.search.Hit;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
 
public class LuceneReadIndexExample
{
	 public static final String INDEX_DIRECTORY = "D:\\IRIS_INDEX\\indexDirectory";
 
    public static void main(String[] args) throws Exception
    {
      
    	//showTop10();
    	//show80Percent();
    	
    	Directory directory = FSDirectory.getDirectory(INDEX_DIRECTORY);
		IndexReader indexReader = IndexReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
    	//searchByBlurb("Tejinder", indexSearcher);
    	searchByBlurb("blurbs: brand AND (brandId : 1)", indexSearcher);
    	
    
    }
     
    public static void showTop10() throws Exception
    {
    	Directory directory = FSDirectory.getDirectory(INDEX_DIRECTORY);
		IndexReader indexReader = IndexReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
    	
		searchByTitle("title:Title2 OR title:bigkk",indexSearcher);
		
		
		List<Integer> titleDocs = searchByTitle("title:State OR title:illicit OR title:trade OR title:cigarettes OR title:South OR title:Africa",indexSearcher);
		
		titleDocs = getTop10(titleDocs);
		
		List<Integer> asDocs = searchByActionStandard("actionStandard:State OR actionStandard:illicit OR actionStandard:trade OR actionStandard:cigarettes OR actionStandard:South OR actionStandard:Africa",indexSearcher);
		//asDocs = getTop10(asDocs);
		
		int size = (asDocs.size() * 10/100) + 1;
		
		int count = 0;
		
		for(Integer dId: asDocs)
		{
			if(!titleDocs.contains(dId))
			{
				if(count <= size)
				{
					titleDocs.add(dId);
					count++;
				}
			}
		}
		
		System.out.println("Final Docs ==");
		
		for(Integer tId:titleDocs)
		{
			System.out.println("Doc Id =="+tId);
		}
    }
    
    public static void show80Percent() throws Exception
    {
    	List<String> keywordList = new ArrayList<String>();
    	keywordList.add("State");
    	keywordList.add("illicit");
    	keywordList.add("trade");
    	keywordList.add("cigarettes");
    	keywordList.add("South");
    	keywordList.add("Africa");
    	
    	
    	Directory directory = FSDirectory.getDirectory(INDEX_DIRECTORY);
		IndexReader indexReader = IndexReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
    	
	
		
		
		List<Integer> titleDocs = searchByTitle("title:State OR title:illicit OR title:trade OR title:cigarettes OR title:South OR title:Africa",indexSearcher, keywordList);
		
		/*List<Integer> asDocs = searchByActionStandard("actionStandard:State OR actionStandard:illicit OR actionStandard:trade OR actionStandard:cigarettes OR actionStandard:South OR actionStandard:Africa",indexSearcher);
	
		
		int size = (asDocs.size() * 10/100) + 1;
		
		int count = 0;
		
		for(Integer dId: asDocs)
		{
			if(!titleDocs.contains(dId))
			{
				if(count <= size)
				{
					titleDocs.add(dId);
					count++;
				}
			}
		}
		*/
		System.out.println("Final Docs ==");
		
		for(Integer tId:titleDocs)
		{
			System.out.println("Doc Id =="+tId);
		}
    	
    }
    
    private static boolean isContain(String source, String subItem){
        String pattern = "\\b"+subItem+"\\b";
        Pattern p=Pattern.compile(pattern);
        Matcher m=p.matcher(source);
        return m.find();
   }
    private static TopDocs searchByFirstName(String firstName, IndexSearcher searcher) throws Exception
    {
        QueryParser qp = new QueryParser("firstName", new StandardAnalyzer());
        Query firstNameQuery = qp.parse(firstName);
        TopDocs hits = searcher.search(firstNameQuery, 10);
        return hits;
    }
 
    private static TopDocs searchById(Integer id, IndexSearcher searcher) throws Exception
    {
        QueryParser qp = new QueryParser("id", new StandardAnalyzer());
        Query idQuery = qp.parse(id.toString());
        TopDocs hits = searcher.search(idQuery, 10);
        return hits;
    }
 /*
    private static IndexSearcher createSearcher() throws IOException {
        Directory dir = FSDirectory.open(Paths.get(INDEX_DIR));
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        return searcher;
    }
    */
    public static void searchIndex(String searchString) throws IOException, ParseException {
		System.out.println("Searching for '" + searchString + "'");
		Directory directory = FSDirectory.getDirectory(INDEX_DIRECTORY);
		IndexReader indexReader = IndexReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);

		Analyzer analyzer = new StandardAnalyzer();
		QueryParser queryParser = new QueryParser("docId", analyzer);
		Query query = queryParser.parse(searchString);
		Hits hits = indexSearcher.search(query);
		System.out.println("Number of hits: " + hits.length());

		Iterator<Hit> it = hits.iterator();
		while (it.hasNext()) {
			Hit hit = it.next();
			Document document = hit.getDocument();
			String path = document.get("docId");
			System.out.println("Hit: " + path);
		}

	}
    
    private static TopDocs searchByDocumentId(Integer docId, IndexSearcher searcher) throws Exception
    {
        QueryParser qp = new QueryParser("docId", new StandardAnalyzer());
        Query docIdQuery = qp.parse(docId.toString());
        TopDocs hits = searcher.search(docIdQuery, 10);
        System.out.println("Number of for DocumentId hits: " + hits.totalHits);
        return hits;
    }
    
    public static List<Integer> searchByTitle(String title, IndexSearcher searcher) throws Exception
    {
        
    	List<Integer> docIdList = new ArrayList<Integer>();
    	
    	QueryParser qp = new QueryParser("title", new StandardAnalyzer());
        Query docIdQuery = qp.parse(title);
        
        docIdQuery.createWeight(searcher);
        TopDocs hits = searcher.search(docIdQuery, 10);
        
        
        System.out.println("Number of for title hits: " + hits.totalHits);
        
        System.out.println("MAX SCORE " + hits.getMaxScore());
        
        Hits ohits = searcher.search(docIdQuery);
        System.out.println("Number of ohits for title hits: " + ohits.length());
        
        
        Iterator<Hit> it = ohits.iterator();
		while (it.hasNext()) {
			Hit hit = it.next();
			Document document = hit.getDocument();
			
			String path = document.get("docId");
			docIdList.add(new Integer(path));
			System.out.println("Title Hit DocumentId: " + path);
		}
		
		 for(ScoreDoc scoreDoc : hits.scoreDocs) {
		      Document doc = searcher.doc(scoreDoc.doc);
		      System.out.println(" Sort Doc Id: "+ doc.get("docId"));
		      System.out.println("Score: "+ scoreDoc.score + " ");
		      System.out.println("Explain -->"+searcher.explain(docIdQuery, scoreDoc.doc).getDescription());
		      
		/*      Explanation explanation = searcher.explain(docIdQuery, scoreDoc.doc); 
		      System.out.println("----------");
		      System.out.println(explanation.toString());
		      System.out.println("Explain Value =="+explanation.getValue());
		      System.out.println("----------");	
		      
		  */   
		   }
		 
		 
		 
        return docIdList;
    }
    
    public static List<Integer> searchByBlurb(String title, IndexSearcher searcher) throws Exception
    {
        
    	List<Integer> docIdList = new ArrayList<Integer>();
    	
    	QueryParser qp = new QueryParser("blurbs", new StandardAnalyzer());
        Query docIdQuery = qp.parse(title);
        
        docIdQuery.createWeight(searcher);
        TopDocs hits = searcher.search(docIdQuery, 10);
        
        
        System.out.println("Number of for blurbs hits: " + hits.totalHits);
        
        System.out.println("MAX SCORE " + hits.getMaxScore());
        
        Hits ohits = searcher.search(docIdQuery);
        System.out.println("Number of ohits for title hits: " + ohits.length());
        
        
        Iterator<Hit> it = ohits.iterator();
		while (it.hasNext()) {
			Hit hit = it.next();
			Document document = hit.getDocument();
			
			String path = document.get("docId");
			docIdList.add(new Integer(path));
			System.out.println("Title Hit DocumentId: " + path);
		}
		
		 for(ScoreDoc scoreDoc : hits.scoreDocs) {
		      Document doc = searcher.doc(scoreDoc.doc);
		      System.out.println(" Sort Doc Id: "+ doc.get("docId"));
		      System.out.println("Score: "+ scoreDoc.score + " ");
		      System.out.println("Explain -->"+searcher.explain(docIdQuery, scoreDoc.doc).getDescription());
		      
		/*      Explanation explanation = searcher.explain(docIdQuery, scoreDoc.doc); 
		      System.out.println("----------");
		      System.out.println(explanation.toString());
		      System.out.println("Explain Value =="+explanation.getValue());
		      System.out.println("----------");	
		      
		  */   
		   }
		 
		 
		 
        return docIdList;
    }
    public static List<Integer> searchByTitle(String title, IndexSearcher searcher, List<String> keywords) throws Exception
    {
        
    	List<Integer> docIdList = new ArrayList<Integer>();
    	
    	QueryParser qp = new QueryParser("title", new StandardAnalyzer());
        Query docIdQuery = qp.parse(title);
        
        docIdQuery.createWeight(searcher);
        TopDocs hits = searcher.search(docIdQuery, 10);
        
        
        System.out.println("Number of for title hits: " + hits.totalHits);
        
        System.out.println("MAX SCORE " + hits.getMaxScore());
        
        Hits ohits = searcher.search(docIdQuery);
        System.out.println("Number of ohits for title hits: " + ohits.length());
        
        
        Iterator<Hit> it = ohits.iterator();
		while (it.hasNext()) {
			Hit hit = it.next();
			Document document = hit.getDocument();
			
			String path = document.get("docId");
			String titleString = document.get("title");
			
			int numberOfMatches = 0;
			for(int j=0;j<keywords.size();j++)
    		{
    			if(isContain(titleString, keywords.get(j)))
    			{
    				numberOfMatches++;
    			}
    		}
			System.out.println("numberOfMatches " + numberOfMatches);
			
			if((keywords.size() * 60/100 )  < numberOfMatches)
			{
				docIdList.add(new Integer(path));
			}
			
			
		}
		
		 for(ScoreDoc scoreDoc : hits.scoreDocs) {
		      Document doc = searcher.doc(scoreDoc.doc);
		      System.out.println(" Sort Doc Id: "+ doc.get("docId"));
		      System.out.println("Score: "+ scoreDoc.score + " ");
		      System.out.println("Explain -->"+searcher.explain(docIdQuery, scoreDoc.doc).getDescription());
		      
		/*      Explanation explanation = searcher.explain(docIdQuery, scoreDoc.doc); 
		      System.out.println("----------");
		      System.out.println(explanation.toString());
		      System.out.println("Explain Value =="+explanation.getValue());
		      System.out.println("----------");	
		      
		  */   
		   }
		 
		 
		 
        return docIdList;
    }
    
   
    
    private static TopDocs searchByResearchStudies(String researchStudies, IndexSearcher searcher) throws Exception
    {
        QueryParser qp = new QueryParser("researchStudies", new StandardAnalyzer());
        Query docIdQuery = qp.parse(researchStudies);
        TopDocs hits = searcher.search(docIdQuery, 10);
        System.out.println("Number of for researchStudies hits: " + hits.totalHits);
        
        Hits ohits = searcher.search(docIdQuery);
        System.out.println("Number of ohits for researchStudies hits: " + ohits.length());
        
        Iterator<Hit> it = ohits.iterator();
		while (it.hasNext()) {
			Hit hit = it.next();
			Document document = hit.getDocument();
			String path = document.get("docId");
			System.out.println("RS Hit DocumentId: " + path);
		}
        return hits;
    }
    
  
    public static List<Integer> searchByActionStandard(String title, IndexSearcher searcher) throws Exception
    {
        
    	List<Integer> docIdList = new ArrayList<Integer>();
    	
    	QueryParser qp = new QueryParser("actionStandard", new StandardAnalyzer());
        Query docIdQuery = qp.parse(title);
        
        docIdQuery.createWeight(searcher);
        TopDocs hits = searcher.search(docIdQuery, 10);
        
        
        System.out.println("Number of for actionStandard hits: " + hits.totalHits);
        
        System.out.println("MAX SCORE " + hits.getMaxScore());
        
        Hits ohits = searcher.search(docIdQuery);
        System.out.println("Number of ohits for actionStandard hits: " + ohits.length());
        
        
        Iterator<Hit> it = ohits.iterator();
		while (it.hasNext()) {
			Hit hit = it.next();
			Document document = hit.getDocument();
			
			String path = document.get("docId");
			docIdList.add(new Integer(path));
			System.out.println("Title Hit DocumentId: " + path);
		}
		
		 for(ScoreDoc scoreDoc : hits.scoreDocs) {
		      Document doc = searcher.doc(scoreDoc.doc);
		      System.out.println(" Sort Doc Id: "+ doc.get("docId"));
		      System.out.println("Score: "+ scoreDoc.score + " ");
		    //  System.out.println("Explain -->"+searcher.explain(docIdQuery, scoreDoc.doc).getDescription());
		      
		 /*     Explanation explanation = searcher.explain(docIdQuery, scoreDoc.doc); 
		      System.out.println("----------");
		      System.out.println(explanation.toString());
		      System.out.println("Explain Value =="+explanation.getValue());
		      System.out.println("----------");	
		   */   
		     
		   }
		 
		 
		 
        return docIdList;
    }
    
    private static TopDocs searchByBusinessQuestion(String businessQuestion, IndexSearcher searcher) throws Exception
    {
        QueryParser qp = new QueryParser("businessQuestion", new StandardAnalyzer());
        Query docIdQuery = qp.parse(businessQuestion);
        TopDocs hits = searcher.search(docIdQuery, 10);
        System.out.println("Number of for businessQuestion hits: " + hits.totalHits);
        
        Hits ohits = searcher.search(docIdQuery);
        System.out.println("Number of ohits for businessQuestion hits: " + ohits.length());
        
        Iterator<Hit> it = ohits.iterator();
		while (it.hasNext()) {
			Hit hit = it.next();
			Document document = hit.getDocument();
			String path = document.get("docId");
			System.out.println("BQ Hit DocumentId: " + path);
		}
		
        return hits;
    }
    
    private static TopDocs searchByMethodology(String methodology, IndexSearcher searcher) throws Exception
    {
        QueryParser qp = new QueryParser("methodology", new StandardAnalyzer());
        Query docIdQuery = qp.parse(methodology);
        TopDocs hits = searcher.search(docIdQuery, 10);
        System.out.println("Number of for methodology hits: " + hits.totalHits);
        
        Hits ohits = searcher.search(docIdQuery);
        System.out.println("Number of ohits for methodology hits: " + ohits.length());
        
        Iterator<Hit> it = ohits.iterator();
		while (it.hasNext()) {
			Hit hit = it.next();
			Document document = hit.getDocument();
			String path = document.get("docId");
			System.out.println("METHODOLOGY Hit DocumentId: " + path);
		}
        return hits;
    }
    
    public static List<Integer> getTop10(List<Integer> docId)
    {
    	List<Integer> top10 = new ArrayList<Integer>();
    	int size = (docId.size() * 10/100) + 1;
    	
    	System.out.println("Size ==>"+ size);
    	
    	for(int i = 0 ;i <=size;i++)
    	{
    		top10.add(docId.get(i));
    	}
    	return top10;
    	
    }
}