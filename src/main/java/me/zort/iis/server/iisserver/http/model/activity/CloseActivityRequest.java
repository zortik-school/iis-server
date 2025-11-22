package me.zort.iis.server.iisserver.http.model.activity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.zort.iis.server.iisserver.domain.campaign.ActivityNoteInput;
import org.jetbrains.annotations.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CloseActivityRequest {
    private Note note;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Note {
        @NotNull
        private String content;

    }

    /**
     * Converts this CloseActivityRequest to an ActivityNoteInput.
     *
     * @param authorId The ID of the author of the note.
     * @return An ActivityNoteInput if a note is present; otherwise, null.
     */
    public @Nullable ActivityNoteInput toActivityNoteInput(long authorId) {
        if (note == null) {
            return null;
        }

        return ActivityNoteInput.builder()
                .authorId(authorId)
                .content(note.getContent())
                .build();
    }
}
