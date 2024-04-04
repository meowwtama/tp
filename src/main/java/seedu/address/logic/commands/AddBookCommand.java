package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKLIST;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BOOK;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.book.Book;




public class AddBookCommand extends Command {
    public static final String COMMAND_WORD = "addbook";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a book to the library "
            + "Example: " + COMMAND_WORD + " "  + PREFIX_BOOKLIST + "The Book of Answers";

    // todo : later need to edit this MESSAGE when the bookTitle recorded to the database.
    public static final String MESSAGE_ADDBOOK_SUCCESS = "Added book successfully";

    private final Book book;

    /**
     * @param index     of the person in the filtered person list to edit the
     *                  merit score
     * @param book of the person donated to be updated to the database
     */
    public AddBookCommand(Book book) {
        requireAllNonNull(book);
        this.book = book;
    }

    
    public CommandResult execute(Model model) throws CommandException {
        
        if (book.equals(new Book(""))) {
            throw new CommandException(Messages.MESSAGE_EMPTY_BOOK_INPUT_FIELD);
        }

        model.addBook(book);
        model.updateFilteredLibraryList(PREDICATE_SHOW_ALL_BOOK);
        return new CommandResult(generateSuccessMessage(book));
    }

    /**
     * Generates a command execution success message when book title is successfully removed
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Book book) {
        return String.format(MESSAGE_ADDBOOK_SUCCESS, book);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddBookCommand)) {
            return false;
        }

        AddBookCommand e = (AddBookCommand) other;
        return book.equals(e.book);
    }
    
}

