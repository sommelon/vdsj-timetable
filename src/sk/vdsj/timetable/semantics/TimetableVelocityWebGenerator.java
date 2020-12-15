package sk.vdsj.timetable.semantics;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import sk.vdsj.timetable.model.Timetable;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TimetableVelocityWebGenerator {

    private String template_name = "timetable.html.vm";

    public TimetableVelocityWebGenerator(){
        // pass
    }

    public TimetableVelocityWebGenerator(String template_name){
        this.template_name = template_name;
    }

    static {
        try {
            Properties properties = new Properties();
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("velocity.properties"));
            Velocity.init(properties);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void generate(Timetable timetable, Writer writer) throws IOException {
        //Zvoľme šablónu
        Template template = Velocity.getTemplate("src/resources/templates/"+this.template_name);

        //Pripravme parametre pre šablónu - objekt timetable
        Map<String, Object> params = new HashMap<>();
        params.put("timetable", timetable);

        //Vykonajme transformáciu šablónou
        VelocityContext context = new VelocityContext(params);
        template.merge(context, writer);
    }
}