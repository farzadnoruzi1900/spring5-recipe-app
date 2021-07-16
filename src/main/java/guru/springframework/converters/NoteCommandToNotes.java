package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.module.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NoteCommandToNotes implements Converter<NotesCommand, Notes> {


    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand source) {
        if (source == null) {
            return null;
        }
        final Notes notes = new Notes();
        notes.setId(source.getId());
        notes.setDirection(source.getRecipeNotes());
        return notes;
    }
}
