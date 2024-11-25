package com.bookso.books.constatns;

/*
* @author: santhosh kumar
* @description: constant values of Books MS
 */

import java.util.Map;

public class BooksConstants {
    //Category Constants
    private static final String FICTION = "FICTION";
    private static final String NON_FICTION = "NON-FICTION";

    //Genre Constants
    public static final String ROMANCE = "ROMANCE";
    public static final String THRILLER = "THRILLER";
    public static final String FANTASY = "FANTASY";
    public static final String SCI_FICTION = "SCIENCE-FICTION";
    public static final String MYSTERY = "MYSTERY";
    public static final String HORROR = "HORROR";
    public static final String ADVENTURE = "ADVENTURE";
    public static final String BIOGRAPHY = "BIOGRAPHY";
    public static final String SELF_HELP = "SELF_HELP";
    public static final String HISTORY = "HISTORY";
    public static final String PHILOSOPHY = "PHILOSOPHY";
    public static final String SCIENCE = "SCIENCE";
    public static final String TRAVEL =  "TRAVEL";

    //Message constants
    public static final String BOOK_CREATED_SC = "Book Created Successfully";
    public static final String BOOK_UPDATED_SC = "Book Updated Successfully";
    public static final String BOOK_DELETED_SC = "Book Deleted Successfully";
    public static final String BOOK_NOT_FOUND_CODE = "Book not found for the given code: %s";
    public static final String BOOK_NOT_FOUND_ID = "Book not found for the given id: %s";
    public static final String BOOK_ALREADY_EXISTS = "Book Already Exists";
    public static final String NO_BOOKS_FOUND = "No Books Found";


    //Maps of the constants
    public static final Map<String, String> genreMaps = Map.ofEntries(
            Map.entry("G01",ROMANCE),
            Map.entry("G02", THRILLER),
            Map.entry("G03", FANTASY),
            Map.entry("G04", SCI_FICTION),
            Map.entry("G05", MYSTERY),
            Map.entry("G06", HORROR),
            Map.entry("G07", ADVENTURE),
            Map.entry("G08", BIOGRAPHY),
            Map.entry("G09", SELF_HELP),
            Map.entry("G10", HISTORY),
            Map.entry("G11", PHILOSOPHY),
            Map.entry("G12", SCIENCE),
            Map.entry("G13", TRAVEL)
    );

    public static final Map<String, String> categoryMaps = Map.ofEntries(
            Map.entry("C01", FICTION),
            Map.entry("C02", NON_FICTION)
    );


}
