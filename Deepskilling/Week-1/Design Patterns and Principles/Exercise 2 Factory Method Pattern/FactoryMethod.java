package javaaa;
interface Document {
 void open();
 void save();
 void close();
}

class WordDocument implements Document {
 @Override
 public void open() {
     System.out.println("Opening Word document...");
 }

 @Override
 public void save() {
     System.out.println("Saving Word document...");
 }

 @Override
 public void close() {
     System.out.println("Closing Word document...");
 }
}

class PdfDocument implements Document {
 @Override
 public void open() {
     System.out.println("Opening PDF document...");
 }

 @Override
 public void save() {
     System.out.println("Saving PDF document...");
 }

 @Override
 public void close() {
     System.out.println("Closing PDF document...");
 }
}

class ExcelDocument implements Document {
 @Override
 public void open() {
     System.out.println("Opening Excel document...");
 }

 @Override
 public void save() {
     System.out.println("Saving Excel document...");
 }

 @Override
 public void close() {
     System.out.println("Closing Excel document...");
 }
}

abstract class DocumentFactory {
 public abstract Document createDocument();
 
 public void processDocument() {
     Document doc = createDocument();
     doc.open();
     doc.save();
     doc.close();
 }
}

class WordDocumentFactory extends DocumentFactory {
 @Override
 public Document createDocument() {
     return new WordDocument();
 }
}

class PdfDocumentFactory extends DocumentFactory {
 @Override
 public Document createDocument() {
     return new PdfDocument();
 }
}

class ExcelDocumentFactory extends DocumentFactory {
 @Override
 public Document createDocument() {
     return new ExcelDocument();
 }
}

public class FactoryMethodPattern {
 public static void main(String[] args) {
     System.out.println("Document Management System using Factory Method Pattern\n");
     
     // Create Word document
     DocumentFactory wordFactory = new WordDocumentFactory();
     Document wordDoc = wordFactory.createDocument();
     System.out.println("\nWorking with Word Document:");
     wordDoc.open();
     wordDoc.save();
     wordDoc.close();
     
     // Create PDF document
     DocumentFactory pdfFactory = new PdfDocumentFactory();
     Document pdfDoc = pdfFactory.createDocument();
     System.out.println("\nWorking with PDF Document:");
     pdfDoc.open();
     pdfDoc.save();
     pdfDoc.close();
     
     // Create Excel document
     DocumentFactory excelFactory = new ExcelDocumentFactory();
     Document excelDoc = excelFactory.createDocument();
     System.out.println("\nWorking with Excel Document:");
     excelDoc.open();
     excelDoc.save();
     excelDoc.close();
     
     // Alternative usage using the template method
     System.out.println("\nAlternative usage with processDocument():");
     System.out.println("Processing Word Document:");
     wordFactory.processDocument();
     
     System.out.println("\nProcessing PDF Document:");
     pdfFactory.processDocument();
     
     System.out.println("\nProcessing Excel Document:");
     excelFactory.processDocument();
 }
}