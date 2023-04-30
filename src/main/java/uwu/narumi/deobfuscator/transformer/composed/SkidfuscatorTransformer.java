package uwu.narumi.deobfuscator.transformer.composed;

import uwu.narumi.deobfuscator.transformer.ComposedTransformer;
import uwu.narumi.deobfuscator.transformer.Transformer;
import uwu.narumi.deobfuscator.transformer.impl.skidfuscator.SkidfuscatorStringTransformer;

import java.util.Arrays;
import java.util.List;

public class SkidfuscatorTransformer extends ComposedTransformer {

    @Override
    public List<Transformer> transformers() {
        return Arrays.asList(
                new SkidfuscatorStringTransformer()
        );
    }

}
