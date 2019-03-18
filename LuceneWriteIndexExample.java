import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.function.IntFieldSource;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.NumericUtils;
import org.apache.poi.util.IntegerField;
 
public class LuceneWriteIndexExample
{
  
    public static final String INDEX_DIRECTORY = "D:\\IRIS_INDEX\\indexDirectory";
    public static void main(String[] args) throws Exception
    {
    	
    	//createIndex();
    	//updateIndex();
    	//deleteIndex("");
    	//createIRISDateIndex();
    	createIRISSynchroCodeIndex();
    }
 
    private static Document createDocument(Integer documentId, String title, String researchStudies, String actionStandard, String businessQuestion, String methodology )
    {
        Document document = new Document();
        
        document.add(new Field("docId", documentId.toString(), Field.Store.YES, Field.Index.UN_TOKENIZED));
        document.add(new Field("title", title, Field.Store.YES, Field.Index.TOKENIZED));
        document.add(new Field("researchStudies", researchStudies, Field.Store.YES, Field.Index.TOKENIZED));
        document.add(new Field("actionStandard", actionStandard, Field.Store.YES, Field.Index.TOKENIZED));
        document.add(new Field("businessQuestion", businessQuestion, Field.Store.YES, Field.Index.TOKENIZED));
        document.add(new Field("methodology", methodology, Field.Store.YES, Field.Index.TOKENIZED));
        return document;
    }
 
	public static void createIndex() throws CorruptIndexException, LockObtainFailedException, IOException {
		Analyzer analyzer = new StandardAnalyzer();
		boolean recreateIndexIfExists = true;
		
		IndexWriter indexWriter = new IndexWriter(INDEX_DIRECTORY, analyzer, recreateIndexIfExists);
        
        
		Document document1 = createDocument(new Integer("1760"), "become Title2", "Research Study 1", "Action checking in one", "Business Question 1", "Methodology 1");
	    indexWriter.addDocument(document1);
	        
	    Document document2 = createDocument(new Integer("1761"), "Title2 is bigkk Title2 Title2", "Research Study 2", "standard1 for second", "Business Question 2", "Methodology 2");
	    
	   /* Document document2 = new Document();
        
	    document2.add(new Field("docId", "1761", Field.Store.YES, Field.Index.UN_TOKENIZED));
	    
	    Field tit = new Field("title", "Title2 is bigkk Title2 Title2", Field.Store.YES, Field.Index.TOKENIZED);
	  //  tit.setBoost(1.5f);
	    
	    document2.add(tit);
	    document2.add(new Field("researchStudies", "Research Study 2", Field.Store.YES, Field.Index.TOKENIZED));
	    document2.add(new Field("actionStandard", "Action Standard 2", Field.Store.YES, Field.Index.TOKENIZED));
	    document2.add(new Field("businessQuestion", "Business Question 2", Field.Store.YES, Field.Index.TOKENIZED));
	    document2.add(new Field("methodology", "Methodology 2", Field.Store.YES, Field.Index.TOKENIZED));
        */
        indexWriter.addDocument(document2);
        
        Document document3 = createDocument(new Integer("1762"), "Title2 is bigss Titl2 asdsfv Title2", "Research Study 3", "third thisr", "Business Question 3", "Methodology 3");
        indexWriter.addDocument(document3);
        
        Document document4 = createDocument(new Integer("1763"), "Title4 is gain", "Research Study 4", "irrelevant text", "Business Question 4", "Methodology 4");
        indexWriter.addDocument(document4);
        
        Document document5 = createDocument(new Integer("1764"), "Title2 is big Title2 best Title2", "Research Study 5", "special characted 3$%!'23e", "Business Question 5", "Methodology 5");
        indexWriter.addDocument(document5);
        
        Document document6 = createDocument(new Integer("1765"), "Title6", "Research Study 6", "Information security of Pall", "Which region in Canada having Pall Mall  as the higest selling product", "Methodology 6");
        indexWriter.addDocument(document6);
        
        Document document7 = createDocument(new Integer("1766"), "Now Belmont in Austria", "Research Study 6", "Web applications accesing Belmont", "Is Belmont exceding it's highest sales or it is degrading its sale in terms of density like illicit Benson & Hedges.", "Methodology 6");
        indexWriter.addDocument(document7);
        
        Document document8 = createDocument(new Integer("1767"), "Benson & Hedges going to hit the market of Middle illicit East trade markets", "Research Study 6", "To have high yielding profits from the Benson & Hedges in middle east like Abu Dhabi,Bahrain,Qatar", "Are we making hikes because of Benson & Hedges brand or some other brands like Pall Mall .", "Methodology 6");
        indexWriter.addDocument(document8);
        
        Document document9 = createDocument(new Integer("1768"), "Benson & Hedges going to hit the market of Middle illicit East trade markets", "Research Study 6", "To sell the product at it's highest value in the market", "Is Carlton good enough for the market", "Methodology 6");
        indexWriter.addDocument(document9);
        
        Document document10 = createDocument(new Integer("1769"), "Brand with the Highest rating in Asia - Dunhill", "Research Study 6", "To make the market profitable we need to have brand like Dunhill and also brand like Carlton.", "Are we covering all the asian countries for our brand Dunhill", "Methodology 6");
        indexWriter.addDocument(document10);
        
        Document document11 = createDocument(new Integer("1770"), "Best selling product In U.S.A is Kent and density product is also Kent", "Research Study 6", "Kent is the main brand which is generating profit for us which is making some sort of effort for us , when we need brand like Kent.Kent is a brand which is recognsied by everyone in U.S.A", "We are not connecting any countries on the brand name Kent and we do expect the that the Kent is a very less potential brand.", "Methodology 6");
        indexWriter.addDocument(document11);
        
        Document document12 = createDocument(new Integer("1771"), "Lucky Strike a big Brand in cigarettes Emma region", "Research Study 6", "Lets have a Brand Lucky Strike equivalent to what we call it as a brand name", "We can keep Pall Mall,Lucky Strike,Carlton and Benson & Hedges here ", "Methodology 6");
        indexWriter.addDocument(document12);
        
        Document document13 = createDocument(new Integer("1772"), "Craven in the market of Craven", "Research Study 6", "To make Craven a global brand name in the market and also make Craven and Kent as the compititor in this area", "Can we make Craven and craven which should pick up things in the market as illicit expected.", "Methodology 6");
        indexWriter.addDocument(document13);
        
        Document document14 = createDocument(new Integer("1773"), "Now we are having a new Brnad Jockey illicit Club", "Research Study 6", "To make new brand more productive", "Lets take a  call to do some marketing of the brand Jockey Club and other branding names like Kent,Pall Mall and Club Jockey,as Jockey Club is new to the market.", "Methodology 6");
        indexWriter.addDocument(document14);
        
        Document document15 = createDocument(new Integer("1774"), "Lucky Strike making a huge profit in market or Lucky strike is the only brand in the market", "Research Study 6", "To know the brand Lucky Strike market ,it it positive or the Lucky Strike is the only brand which is there as of now after the brand Pall Mall,Kent etc.", "Lucky Strike ,Pall Mall,Kent are making a huge profit like Lucky Strike Brand", "Methodology 6");
        indexWriter.addDocument(document15);
        
        Document document16 = createDocument(new Integer("1775"), "Dunhill brand of the year", "Research Study 6", "To know the Dunhill market cap in comparison to other market brands like Pall Mall,Kent,Craven,Jockey Club", "Dunhill,Lucky Strike ,Pall Mall,Kent are making a huge profit like Lucky Strike,Dunhill Brand", "Methodology 6");
        indexWriter.addDocument(document16);
        
        Document document17 = createDocument(new Integer("1776"), "Brand cap of more that one million Derby", "Research Study 6", "To know the Derby market cap in comparison to other market brands like Pall Mall,Kent,Craven,Jockey Club", "We are not connecting any countries on the brand name Derby,Kent and we do expect the that the Kent,Derby is a very less potential brand.", "Methodology 6");
        indexWriter.addDocument(document17);
        
        Document document18 = createDocument(new Integer("1777"), "Brand cap of more that two million Kool", "Research Study 6", "To make new brand more productive", "Lets take a  call to do some marketing of the brand Like Kool Jockey Club and other branding names like Kool,Pall Mall and Kool,as Jockey Club is new to the market.", "Methodology 6");
        indexWriter.addDocument(document18);
        
        Document document19 = createDocument(new Integer("1778"), "John Player illicit and trade John Player South Africa", "Research Study 6", "Lets have a Brand John Player equivalent to what we call it as a brand name", "We can keep John Player,Pall Mall,Lucky Strike,Carlton,John Player and Benson & Hedges ,John Playerhere ", "Methodology 6");
        indexWriter.addDocument(document19);
        
        Document document20 = createDocument(new Integer("1779"), "Minister and Pall Mall", "Research Study 6", "To know the Minister  cap in comparison to other market brands like Minister and cigarettes Pall Mall ,Kent,Craven,Minister and Pall Mall ", "Lets take a  call to do some marketing of the brand Minister and other branding names like Kent,Minister and trade Club Jockey,as Minister is new to the market.", "Methodology 6");
        indexWriter.addDocument(document20);
        
        Document document21 = createDocument(new Integer("1780"), "Prince going to hit the market of Middle East markets", "Research Study 6", "To know the ~ Prince  cap in comparison to other market brands like Prince and Pall Mall ,Kent,Craven,Minister and Pall Mall ", "Lets take a  call to do some marketing of the brand Minister and other branding names like Kent,Minister and Club Jockey,as Minister is new to the market.", "Methodology 6");
        indexWriter.addDocument(document21);
        
        Document document22 = createDocument(new Integer("1781"), "Now Montana in Germany", "Research Study 6", "To know the Montana market cap in comparison to other market brands like Montana,Kent,Craven,Montana", "Montana,Lucky Strike ,Pall Mall,Kent are making a huge profit like Montana Brand", "Methodology 6");
        indexWriter.addDocument(document22);
        
        Document document23 = createDocument(new Integer("1782"), "Free going to hit the market of Middle U.S.A", "Research Study 6", "To know the Free  cap in comparison to other market brands like Prince and Pall Mall ,Kent,Craven,Minister and Pall Mall ", "Lets take a  call to do some marketing of the brand Minister and other branding names like Free,Kent,Minister and Club Jockey,as Minister is new to the market.", "Methodology 6");
        indexWriter.addDocument(document23);
        
        Document document24 = createDocument(new Integer("1783"), "Du Maurier @", "Research Study 6", "To sell the brand Du Maurier at it's highest value in the market", "Is Du Maurier and Du Maurier good enough for the market", "Methodology 6");
        indexWriter.addDocument(document24);
        
        Document document25 = createDocument(new Integer("1784"), "Brand with the illicit Highest rating trade in Asia - MS", "Research Study 6", "To make the market trade profitable we need to have brand like illicit Dunhill and also brand like MS", "Are we covering all the asian countries for our brand Dunhill and MS", "Methodology 6");
        indexWriter.addDocument(document25);
        
        Document document26 = createDocument(new Integer("1785"), "Best selling product In U.S.A is Tekel and density product is also Tekel", "Research Study 6", "Tekel is the main brand which is cigarettes South Africa generating profit for us which is making some sort of effort for us , when we need brand like Kent.Tekel is a brand which is recognsied by everyone in U.S.A", "We are not connecting any countries on the brand name Tekel and we do expect the that the Tekel is a very less potential brand.", "Methodology 6");
        indexWriter.addDocument(document26);
        
        Document document27 = createDocument(new Integer("1786"), "Brand cap of more that one million Yava", "Research Study 6", "To know the Derby market cap in comparison to other market brands like Yava,Kent,Yava,Jockey Club", "We are not connecting any countries on the brand name Derby,Yava and we do expect the that the Kent,Derby is a very less potential brand.", "Methodology 6");
        indexWriter.addDocument(document27);
        
        Document document28 = createDocument(new Integer("1787"), "Rothmans in the market of Craven", "Research Study 6", "To make Rothmans a global illicit brand name in the market and also make Rothmans and Kent as the compititor in this area ", "Can we make Rothmans and craven which should pick up things in the market as expected.", "Methodology 6");
        indexWriter.addDocument(document28);
        
        Document document29 = createDocument(new Integer("1788"), "Viceroy a big Brand in Emma region", "Research Study 6", "Lets have a Brand Viceroy equivalent to what South Africa we call it as a brand name", "We can keep Viceroy,Pall Mall,Lucky Strike,Carlton and Benson & Hedges,Viceroy,Viceroy and Viceroy here ", "Methodology 6");
        indexWriter.addDocument(document29);
        
        Document document30 = createDocument(new Integer("1789"), "Winfield", "Research Study 6", "To sell the brand Winfield at it's highest value cigarettes in the market ", "Is Winfield and Du Maurier good enough for the market", "Methodology 6");
        indexWriter.addDocument(document30);
        
        Document document31 = createDocument(new Integer("1790"), "Brand cap of more that one million Vogue,Dunhill and PallMall", "Research Study 6", "To know the Derby market cap in comparison to other market brands like Pall Mall,Kent,Craven,Jockey Club,Vogue,Dunhill and PallMall", "We are not connecting any countries on the brand name Vogue, Derby,Kent and we do expect the that the Kent,Vogue,Derby is a very less potential brand.", "Methodology 6");
        indexWriter.addDocument(document31);
        
        indexWriter.optimize();
        indexWriter.close();
	}
	
	  private static Document createDocument(Integer documentId, Integer synchroCode, String blurbs, Integer containerId)
	     {
	         Document document = new Document();
	         
	         document.add(new Field("docId", documentId.toString(), Field.Store.YES, Field.Index.UN_TOKENIZED));
	         document.add(new Field("synchroCode", synchroCode.toString(), Field.Store.YES, Field.Index.TOKENIZED));
	         document.add(new Field("blurbs", blurbs, Field.Store.YES, Field.Index.TOKENIZED));
	         document.add(new Field("containerId", containerId.toString(), Field.Store.YES, Field.Index.TOKENIZED));
	       
	         return document;
	     }
	     
	  private static Document createDocumentDate(Integer documentId, Integer synchroCode, String blurbs, Integer finalReportDate, Integer containerId)
	     {
	         Document document = new Document();
	         
	         document.add(new Field("docId", documentId.toString(), Field.Store.YES, Field.Index.UN_TOKENIZED));
	         document.add(new Field("synchroCode", synchroCode.toString(), Field.Store.YES, Field.Index.TOKENIZED));
	         document.add(new Field("blurbs", blurbs, Field.Store.YES, Field.Index.TOKENIZED));
	         //document.add(new NumericField("finalReportDate", finalReportDate.getTime(), Field.Store.YES, Field.Index.UN_TOKENIZED));
	         NumericField nf = new NumericField("final-report-date");
	         nf.setIntValue(finalReportDate);
	         document.add(nf);
	         
	         document.add(new Field("containerId", containerId.toString(), Field.Store.YES, Field.Index.TOKENIZED));
	       
	         return document;
	     }
	  
	  private static Document createDocumentSynchroCode(Integer documentId, Integer synchroCode, String blurbs, Integer finalReportDate, Integer containerId)
	     {
	         Document document = new Document();
	         
	         document.add(new Field("docId", documentId.toString(), Field.Store.YES, Field.Index.UN_TOKENIZED));
	         
	         /*NumericField sC = new NumericField("synchro-code");
	       
	         sC.setIntValue(synchroCode.intValue());
	         document.add(sC);
	         */
	        // document.add(new NumericField("synchro-code", synchroCode.toString(), Field.Store.YES, Field.Index.TOKENIZED));
	      //   document.add(new NumericField("synchro-code", Field.Store.YES, false).setIntValue(synchroCode.intValue()));
	         
	         
	         
	         document.add(new Field("synchro-code", NumericUtils.intToPrefixCoded(synchroCode), Field.Store.YES, Field.Index.TOKENIZED));
	         document.add(new Field("blurbs", blurbs, Field.Store.YES, Field.Index.TOKENIZED));
	         //document.add(new NumericField("finalReportDate", finalReportDate.getTime(), Field.Store.YES, Field.Index.UN_TOKENIZED));
	         NumericField nf = new NumericField("final-report-date");
	         nf.setIntValue(finalReportDate);
	         document.add(nf);
	         
	         document.add(new Field("containerId", containerId.toString(), Field.Store.YES, Field.Index.TOKENIZED));
	       
	         return document;
	     }
	 	public static void createIRISIndex() throws CorruptIndexException, LockObtainFailedException, IOException
	 	{
	 		
	 		Analyzer analyzer = new StandardAnalyzer();
	 		boolean recreateIndexIfExists = true;
	 		
	 		IndexWriter indexWriter = new IndexWriter(INDEX_DIRECTORY, analyzer, recreateIndexIfExists);
	         
	        
	         
	 		 indexWriter.addDocument(createDocument(new Integer("1"), new Integer("123"), "This is Blurbs for Tejinder", new Integer("12")));
	 		 indexWriter.addDocument(createDocument(new Integer("4"), new Integer("1234"), "This is Blurbs for Summet", new Integer("16")));
	         
	         indexWriter.optimize();
	         indexWriter.close();
	 	}
	 	
	 	public static void createIRISDateIndex() throws CorruptIndexException, LockObtainFailedException, IOException
	 	{
	 		
	 		Analyzer analyzer = new StandardAnalyzer();
	 		boolean recreateIndexIfExists = true;
	 		
	 		IndexWriter indexWriter = new IndexWriter(INDEX_DIRECTORY, analyzer, recreateIndexIfExists);
	         
	        Date d1 = new Date("01/01/2018");
	        Date d2 = new Date("02/01/2018");
	         
	 		 indexWriter.addDocument(createDocumentDate(new Integer("1"), new Integer("123"), "This is Blurbs for Tejinder", 2, new Integer("12")));
	 		 indexWriter.addDocument(createDocumentDate(new Integer("4"), new Integer("1234"), "This is Blurbs for Summet", 6, new Integer("16")));
	         
	         indexWriter.optimize();
	         indexWriter.close();
	 	}
	 	
	 	public static void createIRISSynchroCodeIndex() throws CorruptIndexException, LockObtainFailedException, IOException
	 	{
	 		
	 		Analyzer analyzer = new StandardAnalyzer();
	 		boolean recreateIndexIfExists = true;
	 		
	 		IndexWriter indexWriter = new IndexWriter(INDEX_DIRECTORY, analyzer, recreateIndexIfExists);
	         
	       
	 		 indexWriter.addDocument(createDocumentSynchroCode(new Integer("1"), 678, "This is Blurbs for Tejinder", 2, new Integer("12")));
	 		 indexWriter.addDocument(createDocumentSynchroCode(new Integer("4"), 1234, "This is Blurbs for Summet", 6, new Integer("16")));
	         
	         indexWriter.optimize();
	         indexWriter.close();
	 	}
	 	
	 	
	public static void updateIndex() throws CorruptIndexException, LockObtainFailedException, IOException 
	{
		Analyzer analyzer = new StandardAnalyzer();
		boolean recreateIndexIfExists = false;
		
		IndexWriter indexWriter = new IndexWriter(INDEX_DIRECTORY, analyzer, recreateIndexIfExists);
		
		Document document1 = createDocument(new Integer("1766"), "Tejinder1", "Research Study 1", "Action Standard 1", "Business Question 1", "Methodology 1");
		
		Directory directory = new SimpleFSDirectory(new File(INDEX_DIRECTORY));
		indexWriter.unlock(directory);
		
		indexWriter.updateDocument(new Term("docId","1766"), document1);
		
		
	    //indexWriter.addDocument(document1);
	    
	    indexWriter.optimize();
        indexWriter.close();
	}
	
	public static void deleteIndex(String id) {
		
		System.out.println("Deleting index...."+id);
		
		try {
			Term term = new Term("docId","1766");
			
			Directory directory = FSDirectory.open(new File(INDEX_DIRECTORY));
			
			IndexReader indexReader = IndexReader.open(directory, false);
			indexReader.deleteDocuments(term);
			
			indexReader.flush();
			indexReader.close();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}