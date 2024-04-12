package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBooks.getTypicalLibrary;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.book.Book;
import seedu.address.model.library.Library;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class BorrowCommandTest {
    private static final String BOOK_TITLE_STUB = "Book Stub";
    private static final String EMPTY_BOOK_STUB = "";
    private static final String BOOK_TITLE_STUB_1 = "Book Stub 1";
    private static final String BOOK_TITLE_STUB_2 = "Book Stub 2";
    private static final String BOOK_TITLE_STUB_3 = "Book Stub 3";
    private static final Book bookStubObject = new Book(BOOK_TITLE_STUB);
    private static final Book bookStubObject1 = new Book(BOOK_TITLE_STUB_1);
    private static final Book bookStubObject2 = new Book(BOOK_TITLE_STUB_2);
    private static final Book bookStubObject3 = new Book(BOOK_TITLE_STUB_3);

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalLibrary());

    @Test
    public void execute_addBorrowUnfilteredList_success() {
        Person beforeBorrowPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Person afterBorrowPerson = new PersonBuilder(beforeBorrowPerson).withBooks(BOOK_TITLE_STUB).withMeritScore(0).build();

        ObservableList<Book> originalBookList = FXCollections.observableArrayList();
        originalBookList.add(bookStubObject1);
        originalBookList.add(bookStubObject2);
        originalBookList.add(bookStubObject3);
        originalBookList.add(bookStubObject);
        Library originalLibrary = new Library(originalBookList);

        ObservableList<Book> expectedBookList = FXCollections.observableArrayList();
        expectedBookList.add(bookStubObject1);
        expectedBookList.add(bookStubObject2);
        expectedBookList.add(bookStubObject3);
        Library expectedLibrary = new Library(expectedBookList);

        BorrowCommand borrowCommand = new BorrowCommand(INDEX_SECOND_PERSON, bookStubObject);

        String expectedMessage = String.format(BorrowCommand.MESSAGE_ADD_BORROW_SUCCESS, bookStubObject, afterBorrowPerson);

        Model initialModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                originalLibrary);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                expectedLibrary);
        expectedModel.setPerson(beforeBorrowPerson, afterBorrowPerson);

        assertCommandSuccess(borrowCommand, initialModel, expectedMessage, expectedModel);
    }

//    @Test
//    public void execute_addBorrowUnfilteredList_unsuccess() {
//        Person modelPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
//        Person editedPerson = new PersonBuilder(modelPerson).withBooks(BORROW_STUB).withMeritScore(-1).build();
//
//        BorrowCommand borrowCommand = new BorrowCommand(INDEX_FIRST_PERSON,
//                new Book(bookTitle));
//
//        assertThrows(CommandException.class, Messages.MESSAGE_INSUFFICIENT_MERIT_SCORE, () ->
//                borrowCommand.execute(model));
//    }
}
