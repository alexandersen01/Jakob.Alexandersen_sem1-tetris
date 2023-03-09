package no.uib.inf101.tetris.midi;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;

/**
 * Play the Tetris music. Sample usage:
 * <code>
 * TetrisSong music = new TetrisSong();
 * music.run(); 
 * </code>
 */
public class TetrisSong implements Runnable {
    //create a list of all the midi files
    static List<String> midiFiles = Arrays.asList("acdc.midi", "dre.midi", "eminem.midi", "friends.midi",
    "heaven.midi", "hell.midi", "jayz.midi", "meme.midi", "queen.midi", "tetris.midi");
    //pick a random element from midiFiles
    static String randomMidi = midiFiles.get((int) (Math.random() * midiFiles.size()));

    private static final String TETRISMUSIC = randomMidi;
    private Sequencer sequencer;

    @Override
    public void run() {
        InputStream song = TetrisSong.class.getClassLoader().getResourceAsStream(TETRISMUSIC);
        this.doPlayMidi(song, true);
    }

    private void doPlayMidi(final InputStream is, final boolean loop) {
        try {
            this.doStopMidiSounds();
            (this.sequencer = MidiSystem.getSequencer()).setSequence(MidiSystem.getSequence(is));
            if (loop) {
                this.sequencer.setLoopCount(-1);
            }
            this.sequencer.open();
            this.sequencer.start();
        }
        catch (Exception e) {
            this.midiError("" + e);
        }
    }

    public void doStopMidiSounds() {
        try {
            if (this.sequencer == null || !this.sequencer.isRunning()) {
                return;
            }
            this.sequencer.stop();
            this.sequencer.close();
        }
        catch (Exception e) {
            this.midiError("" + e);
        }
        this.sequencer = null;
    }

    public void doPauseMidiSounds() {
        try {
            if (this.sequencer == null || !this.sequencer.isRunning()) {
                return;
            }
            this.sequencer.stop();
        }
        catch (Exception e) {
            this.midiError("" + e);
        }
    }
    
    public void doUnpauseMidiSounds() {
        try {
            if (this.sequencer == null) {
                return;
            }
            this.sequencer.start();
        }
        catch (Exception e) {
            this.midiError("" + e);
        }
    }

    private void midiError(final String msg) {
        System.err.println("Midi error: " + msg);
        this.sequencer = null;
    }
}