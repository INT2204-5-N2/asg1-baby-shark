package speak;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Speak {
    public Speak(String word) {
        Voice voice;
        VoiceManager vm = VoiceManager.getInstance();
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        voice = vm.getVoice("kevin16");
        voice.allocate();
        voice.speak(word);
        voice.deallocate();
    }
}
