package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.module.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToNoteCommand implements Converter<Notes, NotesCommand> {


    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes source) {
        if(source==null){
            return null;
        }
        NotesCommand notesCommand=new NotesCommand();
        notesCommand.setId(source.getId());
        notesCommand.setRecipeNotes(source.getDirection());
        return notesCommand;
    }
}
