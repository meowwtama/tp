package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBooks.GUMIHO;
import static seedu.address.testutil.TypicalBooks.getTypicalLibrary;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.book.Book;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class BorrowCommandTest {
    private static final String BOOK_TITLE_STUB = "Book Stub";
    private static final String EMPTY_BOOK_STUB = "";


    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalLibrary());

    @Test
    public void execute_addBorrowUnfilteredList_success() {
        Book bookStubObject = GUMIHO;
        Person beforeBorrowPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person afterBorrowPerson = new PersonBuilder(beforeBorrowPerson).withBooks("Gumiho").withMeritScore(-1).build();


        BorrowCommand borrowCommand = new BorrowCommand(INDEX_FIRST_PERSON, bookStubObject);

        String expectedMessage = String.format(BorrowCommand.MESSAGE_ADD_BORROW_SUCCESS, bookStubObject,
                afterBorrowPerson);

        Model initialModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                model.getLibrary());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                model.getLibrary());
        expectedModel.setPerson(beforeBorrowPerson, afterBorrowPerson);
        expectedModel.popBookFromLibrary(bookStubObject);

        assertCommandSuccess(borrowCommand, initialModel, expectedMessage, expectedModel);
    }

    /*@Test
    public void execute_addBorrowUnfilteredList_unsuccess() {
        Person modelPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person beforeBorrowPerson = new PersonBuilder(modelPerson).withMeritScore(-10).build();
        Book bookObjectStub = new Book(BOOK_TITLE_STUB);

        BorrowCommand borrowCommand = new BorrowCommand(INDEX_FIRST_PERSON, bookObjectStub);

        assertThrows(CommandException.class, Messages.MESSAGE_INSUFFICIENT_MERIT_SCORE, () ->
                borrowCommand.execute(model));
    }*/
}
