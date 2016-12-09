package agh.cs.lab.constitution;

import agh.cs.lab.constitution.constitutionProcessors.ConstitutionProcessor;
import agh.cs.lab.constitution.constitutionProcessors.IConstitutionProcessor;
import agh.cs.lab.constitution.constitutionProcessors.ISplitterFactory;
import agh.cs.lab.constitution.constitutionProcessors.SplitterFactory;
import agh.cs.lab.constitution.textProcessors.CopyrightLineRemover;
import agh.cs.lab.constitution.textProcessors.DateLineRemover;
import agh.cs.lab.constitution.textProcessors.ITextProcessor;
import agh.cs.lab.constitution.textProcessors.LineJoiner;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paweł Grochola on 09.12.2016.
 */
public class Main {
    public static void main(String args[]) {
        try {
            String constitutionText = readFile("konstytucja.txt", StandardCharsets.UTF_8);
            List<ITextProcessor> textProcessors = new ArrayList<>();
            textProcessors.add(new CopyrightLineRemover());
            textProcessors.add(new DateLineRemover());
            textProcessors.add(new LineJoiner());

            ISplitterFactory splitterFactory = new SplitterFactory();
            IConstitutionProcessor constitutionProcessor = new ConstitutionProcessor(splitterFactory);
            textProcessors.forEach(constitutionProcessor::addTextProcessor);
            IConstitution constitution = constitutionProcessor.processText(constitutionText);
            for(IChapter chapter : constitution.getChapters()) {
                System.out.println(chapter.getChapterNo());
                for(IArticle article : chapter.getArticles()) {
                    System.out.print("\t"+article.getArticleNo());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
