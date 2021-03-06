package eflang.ear.jit;

import eflang.core.MusicSource;
import eflang.ear.core.Instruction;
import eflang.ear.core.StatefulInstructionCompiler;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class EARInstructionMusicSource implements MusicSource {
    private StatefulInstructionCompiler instructionCompiler;
    private List<Instruction> instructions;
    private Iterator<String> compiledInstruction;
    private long pos;

    public EARInstructionMusicSource(StatefulInstructionCompiler instructionCompiler, List<Instruction> instructions) {
        this.instructionCompiler = instructionCompiler;
        this.instructions = instructions;
        this.compiledInstruction = Collections.emptyIterator();
    }

    @Override
    public long getPos() {
        return pos;
    }

    @Override
    public void seek(long pos) {
        this.pos = pos;
    }

    @Override
    public boolean hasNext() {
        if (compiledInstruction.hasNext()) {
            return true;
        }

        try {
            compileNextInstruction();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public String next() {
        if (!compiledInstruction.hasNext()) {
            compileNextInstruction();
        }

        return compiledInstruction.next();
    }

    private void compileNextInstruction() {
        while (!compiledInstruction.hasNext()) {
            if (pos >= instructions.size()) {
                throw new NoSuchElementException();
            }
            compiledInstruction = instructionCompiler.compileInstruction(instructions.get((int)pos)).iterator();
            pos++;
        }
    }
}
