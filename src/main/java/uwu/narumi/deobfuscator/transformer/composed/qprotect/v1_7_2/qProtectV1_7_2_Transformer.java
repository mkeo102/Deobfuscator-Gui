package uwu.narumi.deobfuscator.transformer.composed.qprotect.v1_7_2;

import uwu.narumi.deobfuscator.transformer.ComposedTransformer;
import uwu.narumi.deobfuscator.transformer.Transformer;
import uwu.narumi.deobfuscator.transformer.impl.qprotect.latest.qProtectNumberTransformer;
import uwu.narumi.deobfuscator.transformer.impl.qprotect.v1_7_2.qProtectNumberPoolTransformer;
import uwu.narumi.deobfuscator.transformer.impl.universal.other.UnHideTransformer;
import uwu.narumi.deobfuscator.transformer.impl.universal.remove.MethodLocalsAndParametersRemoveTransformer;

import java.util.Arrays;
import java.util.List;

public class qProtectV1_7_2_Transformer extends ComposedTransformer {

    @Override
    public List<Transformer> transformers() {
        return Arrays.asList(
                new qProtectNumberTransformer(),
                new qProtectNumberPoolTransformer(),
                new MethodLocalsAndParametersRemoveTransformer(),
                new UnHideTransformer()
        );
    }
}
