import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MonsterNames {
    String[] str = {"Gomba", "Ulkoo", "Grutong", "Gnomy", "Lorpun", "Crysher", "Fattiyas", "Bom-bomp",
    "Scolator", "Gargantua", "Skinnabishu", "Gritchuar", "Freeaklum", "Swampool", "Creikus", "Rubjong", "Whoalap",
    "Byfur", "Groppol", "Vnorkaz", "Griddapp", "Zraqu", "Kroopy", "Ullow", "Umrimz", "Droom", "Hosh-hush", "Palladin",
    "Boolzyas", "Eulk"};

    ArrayList<String> monsterList = new ArrayList<>(Arrays.asList(str));
    private static Random staticRandom = new Random();
    String currentMonsterName;

    public String getRandomMonsterName () {
        if (monsterList.size() <= 1) {
            monsterList = new ArrayList<>(Arrays.asList(str));
        }
        int randomIndex = staticRandom.nextInt(monsterList.size() );
        currentMonsterName = monsterList.get(randomIndex);
        monsterList.remove(randomIndex);
        return currentMonsterName;
    }
}

