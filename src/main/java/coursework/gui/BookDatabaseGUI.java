package coursework.gui;

import coursework.model.Author;
import coursework.DatabaseHelper;
import coursework.model.Book;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class BookDatabaseGUI extends JFrame {
    private JPanel databasePanel;

    // Book
    private JPanel booksPanel;
    private JTextField textFieldBookTitle;
    private JTextField textFieldYearOfPublication;
    private JTextField textFieldSubject;
    private JTextField textFieldStatus;
    private JButton saveBookButton;

    private JButton editButton;
    private JTable bookTable;
    private JScrollPane scrollPane;
    private JPanel booksListPanel;
    private JButton editBookButton;
    private JButton deleteBookButton;

    // Search Book
    private JTextField searchBooksTextField;
    private JButton searchBookButton;

    private JButton managePublishersButton;
    private JComboBox cbPublishers;
    private JComboBox cbAuthors;
    private JButton manageAuthorsButton;
    private JLabel labelSelectedAuthors;
    private JLabel labelSelectedPublishers;

    private final DatabaseHelper dbHelper = new DatabaseHelper();

    public BookDatabaseGUI() throws FileNotFoundException {
        showBooks();
        editBookButton.addActionListener(e -> editBook());
        deleteBookButton.addActionListener(e -> deleteBook());
    }

    public static void main(String[] args) throws FileNotFoundException {
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

    public void showBooks() throws FileNotFoundException {
        List<Book> listOfBooksFromFile = dbHelper.filesToRows(new FileInputStream("src/data/Books.csv"));

        for (Book book : listOfBooksFromFile) {

            String authorName = book.getAuthor().component1() + " " + book.getAuthor().component2();

            Object[] par = new Object[]{
                    book.getSubject(),
                    book.getTitle(),
                    book.getStatus(),
                    authorName,
                    book.getYearOfPublication(),
                    book.getPublisher(),
            };

            ((DefaultTableModel) bookTable.getModel()).addRow(par);

        }
    }

    private void editBook() {

        // {0 - "Type", 1 - "Name", 2 - "Status", 3 - "Author", 4 - "Year", 5 - "Publisher"}

        int selectedRow = bookTable.getSelectedRow();
        String author = bookTable.getValueAt(selectedRow, 3).toString();
        List<String> authorNames = List.of(author.split(" "));
        Book selectedBook = new Book(
                bookTable.getValueAt(selectedRow, 1).toString(),
                new Author(authorNames.get(0), authorNames.get(1)),
                bookTable.getValueAt(selectedRow, 2).toString(),
                bookTable.getValueAt(selectedRow, 4).toString(),
                bookTable.getValueAt(selectedRow, 5).toString(),
                bookTable.getValueAt(selectedRow, 0).toString()
        );

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
            // remove selected row from the model
            if (JOptionPane
                    .showConfirmDialog(
                            null, "Are you sure you want to delete this book?"
                    ) == 0) {
                ((DefaultTableModel) bookTable.getModel()).removeRow(bookTable.getSelectedRow());
            }
            // .showMessageDialog(null, "Selected row deleted successfully");
        }
    }
}
