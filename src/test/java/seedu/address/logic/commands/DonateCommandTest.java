package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalBooks.getTypicalLibrary;
import static seedu.address.testutil.TypicalIndexes.INDEX_KEPLER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.KEPLER;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.book.Book;
import seedu.address.model.library.Library;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class DonateCommandTest {
    private static final int MERIT_SCORE_STUB = 2;
    private static final String BOOK_TITLE_STUB = "Some book";
    private static final String EMPTY_BOOK_STUB = "";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalLibrary());

    @Test
    public void execute_returnUnfilteredList_success() {
        Person editedPerson = new PersonBuilder(KEPLER).withMeritScore(MERIT_SCORE_STUB).build();

        DonateCommand donateCommand = new DonateCommand(INDEX_KEPLER, new Book(BOOK_TITLE_STUB));

        String expectedMessage = String.format(DonateCommand.MESSAGE_DONATE_SUCCESS,
                editedPerson, new Book(BOOK_TITLE_STUB));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                new Library(model.getLibrary().getBookList()));
        expectedModel.setPerson(KEPLER, editedPerson);
        expectedModel.addBook(new Book(BOOK_TITLE_STUB));
        assertCommandSuccess(donateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Index outOfBoundIndex = INDEX_THIRD_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DonateCommand donateCommand = new DonateCommand(outOfBoundIndex, new Book(BOOK_TITLE_STUB));

        assertCommandFailure(donateCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }


    @Test
    public void empty_bookTitle_failure() {
        DonateCommand donateCommand = new DonateCommand(INDEX_KEPLER, new Book(EMPTY_BOOK_STUB));
        String expectedMessage = Messages.MESSAGE_EMPTY_BOOK_INPUT_FIELD;

        assertCommandFailure(donateCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        DonateCommand firstDonateCommand = new DonateCommand(INDEX_SECOND_PERSON, new Book(EMPTY_BOOK_STUB));
        DonateCommand secondDonateCommand = new DonateCommand(INDEX_THIRD_PERSON, new Book(EMPTY_BOOK_STUB));
        DonateCommand thirdDonateCommand = new DonateCommand(INDEX_SECOND_PERSON, new Book(BOOK_TITLE_STUB));

        // same object -> returns true
        assertTrue(firstDonateCommand.equals(firstDonateCommand));

        // same values -> returns true
        DonateCommand firstDonateCommandCopy = new DonateCommand(INDEX_SECOND_PERSON, new Book(EMPTY_BOOK_STUB));
        assertTrue(firstDonateCommand.equals(firstDonateCommandCopy));

        // different types -> returns false
        assertFalse(firstDonateCommand.equals(true));

        // null -> returns false
        assertFalse(firstDonateCommand.equals(null));

        // different person -> returns false
        assertFalse(firstDonateCommand.equals(secondDonateCommand));

        // different book -> returns false
        assertFalse(firstDonateCommand.equals(thirdDonateCommand));
    }
}
