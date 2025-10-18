package linguistic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CommandVocabulary {
    private final Map<String, CommandType> vocabulary = new HashMap<>();

    // These are all the possible words which the program can
    // recognise
    public CommandVocabulary() {
        vocabulary.put("create", CommandType.VERB);
        vocabulary.put("code", CommandType.VERB);
        vocabulary.put("read", CommandType.VERB);
        vocabulary.put("write", CommandType.VERB);
        vocabulary.put("talk", CommandType.VERB);
        vocabulary.put("film", CommandType.VERB);
        vocabulary.put("hear", CommandType.VERB);
        vocabulary.put("calculate", CommandType.VERB);
        vocabulary.put("research", CommandType.VERB);
        vocabulary.put("present", CommandType.VERB);
        vocabulary.put("add", CommandType.VERB);
        vocabulary.put("remove", CommandType.VERB);
        vocabulary.put("delete", CommandType.VERB);
        vocabulary.put("update", CommandType.VERB);
        vocabulary.put("edit", CommandType.VERB);
        vocabulary.put("complete", CommandType.VERB);
        vocabulary.put("finish", CommandType.VERB);
        vocabulary.put("schedule", CommandType.VERB);
        vocabulary.put("plan", CommandType.VERB);
        vocabulary.put("organize", CommandType.VERB);
        vocabulary.put("remind", CommandType.VERB);
        vocabulary.put("list", CommandType.VERB);
        vocabulary.put("show", CommandType.VERB);
        vocabulary.put("check", CommandType.VERB);
        vocabulary.put("prioritize", CommandType.VERB);
        vocabulary.put("start", CommandType.VERB);
        vocabulary.put("stop", CommandType.VERB);

        vocabulary.put("German", CommandType.LANGUAGE);
        vocabulary.put("English", CommandType.LANGUAGE);
        vocabulary.put("French", CommandType.LANGUAGE);
        vocabulary.put("Spanish", CommandType.LANGUAGE);
        vocabulary.put("Turkish", CommandType.LANGUAGE);
        vocabulary.put("Japanese", CommandType.LANGUAGE);
        vocabulary.put("Chinese", CommandType.LANGUAGE);
        vocabulary.put("Korean", CommandType.LANGUAGE);


        vocabulary.put("economics", CommandType.SUBJECT);
        vocabulary.put("mathematics", CommandType.SUBJECT);
        vocabulary.put("math", CommandType.SUBJECT);
        vocabulary.put("history", CommandType.SUBJECT);
        vocabulary.put("chemistry", CommandType.SUBJECT);
        vocabulary.put("physics", CommandType.SUBJECT);
        vocabulary.put("biology", CommandType.SUBJECT);
        vocabulary.put("geography", CommandType.SUBJECT);
        vocabulary.put("philosophy", CommandType.SUBJECT);
        vocabulary.put("computer science", CommandType.SUBJECT);
        vocabulary.put("programming", CommandType.SUBJECT);
        vocabulary.put("literature", CommandType.SUBJECT);


        vocabulary.put("task", CommandType.GENERAL);
        vocabulary.put("book", CommandType.GENERAL);
        vocabulary.put("computer", CommandType.GENERAL);
        vocabulary.put("laptop", CommandType.GENERAL);
        vocabulary.put("email", CommandType.GENERAL);
        vocabulary.put("project", CommandType.GENERAL);
        vocabulary.put("assignment", CommandType.GENERAL);
        vocabulary.put("meeting", CommandType.GENERAL);
        vocabulary.put("goal", CommandType.GENERAL);
        vocabulary.put("deadline", CommandType.GENERAL);
        vocabulary.put("reminder", CommandType.GENERAL);
        vocabulary.put("note", CommandType.GENERAL);
        vocabulary.put("tasklist", CommandType.GENERAL);
        vocabulary.put("event", CommandType.GENERAL);
        vocabulary.put("events", CommandType.GENERAL);

        vocabulary.put("I", CommandType.GENERAL);
        vocabulary.put("me", CommandType.GENERAL);
        vocabulary.put("she", CommandType.GENERAL);
        vocabulary.put("her", CommandType.GENERAL);
        vocabulary.put("he", CommandType.GENERAL);
        vocabulary.put("him", CommandType.GENERAL);
        vocabulary.put("they", CommandType.GENERAL);
        vocabulary.put("them", CommandType.GENERAL);
        vocabulary.put("our", CommandType.GENERAL);

    }

    public ArrayList<String> getVocabulary() {
       ArrayList<String> commands = new ArrayList<String>();
       for(Map.Entry<String, CommandType> entry : vocabulary.entrySet()){
           commands.add(entry.getKey());
       }
        return commands;
    }
}
