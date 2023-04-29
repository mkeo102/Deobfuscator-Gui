package uwu.narumi.deobfuscator.transformer.composed;

import uwu.narumi.deobfuscator.transformer.ComposedTransformer;
import uwu.narumi.deobfuscator.transformer.Transformer;
import uwu.narumi.deobfuscator.transformer.impl.caesium.CaesiumInvokeDynamicTransformer;
import uwu.narumi.deobfuscator.transformer.impl.caesium.CaesiumNumberPoolTransformer;
import uwu.narumi.deobfuscator.transformer.impl.caesium.CaesiumNumberTransformer;
import uwu.narumi.deobfuscator.transformer.impl.caesium.CaesiumStringTransformer;
import uwu.narumi.deobfuscator.transformer.impl.universal.other.UniversalNumberTransformer;
import uwu.narumi.deobfuscator.transformer.impl.universal.remove.ImageCrasherRemoveTransformer;
import uwu.narumi.deobfuscator.transformer.impl.universal.remove.InvalidAnnotationRemoveTransformer;
import uwu.narumi.deobfuscator.transformer.impl.universal.remove.NopRemoveTransformer;
import uwu.narumi.deobfuscator.transformer.impl.universal.remove.TrashPopRemoveTransformer;

import java.util.Arrays;
import java.util.List;

public class CaesiumTransformer extends ComposedTransformer {

    @Override
    public List<Transformer> transformers() {
        return Arrays.asList(
                new ImageCrasherRemoveTransformer(),
                new InvalidAnnotationRemoveTransformer(),
                new TrashPopRemoveTransformer(),
                new NopRemoveTransformer(),
                new UniversalNumberTransformer(),
                new CaesiumNumberTransformer(),
                new CaesiumNumberPoolTransformer(),
                new CaesiumInvokeDynamicTransformer(),
                new CaesiumNumberTransformer(),
                new CaesiumNumberPoolTransformer(),
                new UniversalNumberTransformer(),
                new CaesiumStringTransformer()
        );
    }
}
