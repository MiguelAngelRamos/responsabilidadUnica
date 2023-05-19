package cl.miguelramos.solid.srp.badexample;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class Book {

/* primer motivo para cambiar que cambie la información del libro
 si queremos añadir un año de salida del libro o editorial
 el libro va a cambiar y eso es una razón de cambio */
  public static final String BOOK_DIRECTORY_PATH = "/tmp";

  private String title;
  private String author;
  private List<Page> pages;
  private Page currentPage;

  public Book(String title, String author, List<Page> pages) {
    this.title = title;
    this.author = author;
    this.pages = pages;
    this.currentPage = this.pages.get(0);
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public Page getCurrentPage() {
    return currentPage;
  }

  public void turnPage() {
    Integer nextPageIndex = currentPage.getNumber();
    if (nextPageIndex <= pages.size()) {
      currentPage = pages.get(nextPageIndex);
    }
  }

  public void turnPageBack() {
    Integer previousPageIndex = currentPage.getNumber() - 2;
    if (previousPageIndex >= 0) {
      currentPage = pages.get(previousPageIndex);
    }
  }

  /* puede cambiar la forma en la que se guarda
  *  - Cambia ahora se guarda en base de datos,
  *  - Ahora se va a guardar una web
  *  En definitiva cambia la forma en la que se guarda
  * */
  public void save() throws IOException {
    String bookFilePath =
            Book.BOOK_DIRECTORY_PATH + "/" + this.title + "_" + new Date().getTime();
    BufferedWriter writer = new BufferedWriter(new FileWriter(bookFilePath));
    for (Page page : pages) {
      writer.write("---- Page " + page.getNumber() + " ----");
      writer.newLine();
      writer.write(page.getContent());
      writer.newLine();
    }
    writer.close();
  }

}

/*
*  Por lo tanto esta clase tiene 2 formas de cambiar totalmente distintos, por lo que incumple el SRP
* */