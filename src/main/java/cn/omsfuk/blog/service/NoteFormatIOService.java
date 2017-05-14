package cn.omsfuk.blog.service;

import cn.omsfuk.blog.domain.Note;
import cn.omsfuk.blog.service.NoteFormatIOService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by omsfuk on 17-5-6.
 */

@Service
@Scope("prototype")
public class NoteFormatIOService {

    public static Pattern PATTERN_TITLE = Pattern.compile(" *title *: *(.+)");
    public static Pattern PATTERN_TAGS = Pattern.compile(" *tags *: *(.+)");
    public static Pattern PATTERN_PERMALINK = Pattern.compile(" *permalink *: *(.+)");

    private String header;

    private String body;

    private void resolveHeader(Note note, String header) {

        Matcher mc = PATTERN_TITLE.matcher(header);
        if(mc.find()) {
            note.setTitle(mc.group(1));
        } else {
            note.setTitle("无题");
        }

        mc = PATTERN_TAGS.matcher(header);
        if(mc.find()) {
            String tags = mc.group(1);
            tags = tags.replaceAll(", *", ",");
            note.setTags(tags);
        } else {
            note.setTags("");
        }

        mc = PATTERN_PERMALINK.matcher(header);
        if(mc.find()) {
            note.setUrl(mc.group(1));
        } else {
            note.setUrl("" + System.currentTimeMillis());
        }

    }

    /**
     * 解析头部，至少8个连字符
     * @param content
     * @return
     */
    private boolean splitHeaderAndBody(String content) {
        StringBuilder sbbody = new StringBuilder();
        header = new String();
        Scanner scanner = new Scanner(content);
        String line = scanner.nextLine();
        if(line.startsWith("--------")) {
            line = scanner.nextLine();
            while(!line.startsWith("--------")) {
                header += line + "\r\n";
                if(scanner.hasNextLine()) {
                    line = scanner.nextLine();
                } else {
                    return false;
                }
            }
        } else {
            sbbody.append(line);
            sbbody.append("\r\n");
        }

        while(scanner.hasNextLine()) {
            sbbody.append(scanner.nextLine());
            sbbody.append("\r\n");
        }
        body = sbbody.toString();
        return true;
    }

    
    public Note resolveRawNote(String content) {
        Note note = new Note();
        if(!splitHeaderAndBody(content)) {
            return null;
        }
        resolveHeader(note, header);
        note.setContent(body);
        return note;
    }
}
