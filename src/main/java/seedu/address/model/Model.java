package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.book.Book;
import seedu.address.model.library.Threshold;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Book> PREDICATE_SHOW_ALL_BOOK = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Replaces library data with the data in {@code library}.
     */
    void setLibrary(ReadOnlyLibrary library);

    /** Returns the Library */
    ReadOnlyLibrary getLibrary();

    /**
     * Updates the filter of the filtered book list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredLibraryList(Predicate<Book> predicate);
    /** Returns the list of books in the Library **/
    ObservableList<Book> getLibraryBookList();

    /**
     * Adds {@code book} to the Library.
     */
    void addBook(Book book);

    /**
     * Deletes book at {@code index} from the Library.
     */
    void deleteBook(int index);

    /**
     * Returns true if {@code person} can borrow from the library.
     */
    boolean canLendTo(Person person);

    /**
     * Sets threshold of the library.
     */
    void setThreshold(Threshold threshold);

    /**
     * Returns the threshold of the library.
     */
    Threshold getThreshold();

    /**
     * Returns true if threshold of library is the same as {@code threshold}.
     */
    boolean hasThreshold(Threshold threshold);

    /**
     * Returns true if book is inside the library book list.
     */
    boolean hasBookInLibrary(Book book);

    /**
     * Returns the {@code book} if it is present in the library and removes it from the library book list.
     * Returns null if book is not present in the library book list.
     */
    Book popBookFromLibrary(Book book);
}
