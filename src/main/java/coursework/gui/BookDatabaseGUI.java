package coursework.gui;

import coursework.DDBB;
import coursework.algorithms.BubbleSort;
import coursework.algorithms.MergeSort;
import coursework.database.AUTHORS;
import coursework.database.BOOKS;
import coursework.database.PUBLISHERS;
import coursework.model.Author;
import coursework.model.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookDatabaseGUI extends JFrame {
    private JPanel databasePanel;

    public JFrame publisherFrame;
    private final DDBB databaseManager = DDBB.INSTANCE;

    // Book
    private JTextField textFieldBookTitle;
    private JTextField textFieldYearOfPublication;
    private JTextField textFieldSubject;
    private JTextField textFieldStatus;
    private JButton saveBookButton;

    private JTable bookTable;
    private JScrollPane scrollPane;
    private JPanel booksListPanel;
    private JPanel booksPanel;
    private JButton editBookButton;
    private JButton deleteBookButton;

    private JButton searchBookButton;

    private JButton managePublishersButton;
    private JComboBox<PUBLISHERS> cbPublishers;
    private JComboBox<AUTHORS> cbAuthors;
    private JButton manageAuthorsButton;
    private JLabel labelSelectedAuthors;
    private JLabel labelSelectedPublishers;
    private JButton radixSortButton;
    private JButton mergeSortButton;
    private JRadioButton sortByBookTitleRadioButton;
    private JRadioButton sortByYearOfRadioButton;
    private JLabel numberOfTicksJLabel;

    private final ArrayList<BOOKS> listOfBooksFromFile = new ArrayList<>();

    public BookDatabaseGUI() {


        ButtonGroup group = new ButtonGroup();
        group.add(sortByBookTitleRadioButton);
        group.add(sortByYearOfRadioButton);

        listOfBooksFromFile.addAll(databaseManager.getBooks());

        showBooks(listOfBooksFromFile);

        List<PUBLISHERS> publishers = databaseManager.getPublishers();
        cbPublishers.setModel(new DefaultComboBoxModel<>(publishers.toArray(new PUBLISHERS[0])));

        cbPublishers.addItemListener(e -> {
            String publisher = Objects.requireNonNull(cbPublishers.getSelectedItem()).toString().split(":")[2].replace("]", "");
            labelSelectedPublishers.setText(publisher);
        });

        List<AUTHORS> authors = databaseManager.getAuthors();
        cbAuthors.setModel(new DefaultComboBoxModel<>(authors.toArray(new AUTHORS[0])));
        cbAuthors.addItemListener(e -> {
            String author = Objects.requireNonNull(cbAuthors.getSelectedItem()).toString().split(":")[2].replace("]", "");
            labelSelectedAuthors.setText(author);
        });
        saveBookButton.addActionListener(e -> addBook());

        managePublishersButton.addActionListener(e -> PublishersGUI.main(null));
        manageAuthorsButton.addActionListener(e -> AuthorsGUI.main(null));

        searchBookButton.addActionListener(e -> doBubbleSort(sortType()));
        mergeSortButton.addActionListener(e -> doMergeSort(sortType()));

    }

    private int sortType() {

        if (sortByBookTitleRadioButton.isSelected()) {
            return 1;
        } else {
            return 2;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("BookDatabaseGUI");
        frame.setContentPane(new BookDatabaseGUI().databasePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        Object[] cols = {"Type", "Name", "Status", "Author", "Year", "Publisher"};
        bookTable = new JTable(new DefaultTableModel(cols, 0));
        scrollPane = new JScrollPane(bookTable);
    }

    public void showBooks(ArrayList<BOOKS> allBooks) {
        for (BOOKS book : allBooks) {
            String authorName = book.getBOOK_AUTHOR();
            Object[] par = new Object[]{book.getBOOK_TYPE(), book.getBOOK_NAME(), book.getBOOK_STATUS(), authorName, book.getYEAR(), book.getPUBLISHER(),};
            ((DefaultTableModel) bookTable.getModel()).addRow(par);
        }
    }

    private void doBubbleSort(int sortType) {
        BubbleSort bubbleSort = new BubbleSort();
        ArrayList<BOOKS> sortedList = new ArrayList<>(bubbleSort.sortByBubbleSort((ArrayList<BOOKS>) databaseManager.getBooks(), sortType).getFirst());
        numberOfTicksJLabel.setText(String.valueOf(bubbleSort.sortByBubbleSort(listOfBooksFromFile, sortType).getSecond()));
        ((DefaultTableModel) bookTable.getModel()).getDataVector().removeAllElements();
        showBooks(sortedList);
    }

    private void doMergeSort(int sortType) {
        MergeSort mergeSort = new MergeSort();
        ArrayList<BOOKS> sortedList = new ArrayList<>(mergeSort.mergeSort((ArrayList<BOOKS>) databaseManager.getBooks(), sortType).getFirst());
        numberOfTicksJLabel.setText(String.valueOf(mergeSort.mergeSort(listOfBooksFromFile, sortType).getSecond()));
        ((DefaultTableModel) bookTable.getModel()).getDataVector().removeAllElements();
        showBooks(sortedList);
    }

    private void editBook() {
        int selectedRow = bookTable.getSelectedRow();
        String author = bookTable.getValueAt(selectedRow, 3).toString();
        List<String> authorNames = List.of(author.split(" "));
        Book selectedBook = new Book(bookTable.getValueAt(selectedRow, 1).toString(),
                new Author(authorNames.get(0), authorNames.get(1)), bookTable.getValueAt(selectedRow,
                2).toString(), bookTable.getValueAt(selectedRow, 4).toString(),
                bookTable.getValueAt(selectedRow, 5).toString(),
                bookTable.getValueAt(selectedRow, 0).toString());

        System.out.println("Type/Subject " + selectedBook.component6());
        System.out.println("Name " + selectedBook.component1());
        System.out.println("Status " + selectedBook.component2());
        System.out.println("Author " + selectedBook.component3());
        System.out.println("Year " + selectedBook.component4());
        System.out.println("Publisher " + selectedBook.component5());

        textFieldBookTitle.setText(selectedBook.getTitle());
        textFieldYearOfPublication.setText(selectedBook.getYearOfPublication());
        textFieldSubject.setText(selectedBook.getSubject());
        textFieldStatus.setText(selectedBook.getStatus());
        cbPublishers.setSelectedItem(selectedBook.component5());
        labelSelectedAuthors.setText(author);
    }

    private void deleteBook() {
        if (bookTable.getSelectedRow() != -1) {
            if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this book?") == 0) {
                // databaseManager.deleteBook(1);
                ((DefaultTableModel) bookTable.getModel()).removeRow(bookTable.getSelectedRow());
            }
        }
    }

    private void addBook() {
        if (validFields()) {
            JOptionPane.showMessageDialog(null, "Please ensure all fields are correctly filled!");
        } else {
            Book newBook = new Book(
                    textFieldBookTitle.getText(),
                    new Author("No", "Book"),
                    textFieldStatus.getText(),
                    textFieldYearOfPublication.getText(),
                    "No Publisher",
                    textFieldSubject.getText()
            );
            databaseManager.addBook(newBook);
        }
        ((DefaultTableModel) bookTable.getModel()).getDataVector().removeAllElements();
        showBooks((ArrayList<BOOKS>) databaseManager.getBooks()); // investigate how to be passively notified by controller [no need to call show books]
    }

    private boolean validFields() {
        return textFieldBookTitle.getText().isEmpty() ||
                textFieldStatus.getText().isEmpty() ||
                textFieldYearOfPublication.getText().isEmpty() ||
                textFieldSubject.getText().isEmpty();
    }


}
