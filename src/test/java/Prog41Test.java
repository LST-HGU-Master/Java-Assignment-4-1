import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.*;
/**
 * ＠version (20220501) 1st version
 * @version (20261007) int型への変換チェックテストを加え、４つに分割した
 **/
public class Prog41Test {

    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private ByteArrayOutputStream bos;
    
    @BeforeEach
    public void setUp() {
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }    
    
    /* int型への変換チェック */
    @Test
    public void testIntTypeConversion() {
        assertThrows(NumberFormatException.class, () -> {
            Prog41.main(new String[]{"a", "1", "2"});
        }, "実行時引数を int 型（Integer.parseInt）に変換していません。課題の指示に従ってください。");
    }
    
    /* カンマ区切り出力（末尾カンマ不可）
     * 末尾カンマなしで数値が出力されているか検証（改行の有無は問わない）
    */
    @Test
    public void testStandardOutput() {
        Prog41.main(new String[]{"3", "1", "2"});
        
        String actual = bos.toString().trim();
        assertEquals("3,1,2", actual,
            "引数 「3 1 2」 に対する出力結果が異なります。"
        );
    }
    
    /* 別ケースの出力検証（ハードコーディング対策 / 改行の有無は問わない）
    */
    @Test
    public void testDifferentValuesOutput() {
        Prog41.main(new String[]{"-5", "0", "10"});
        
        String actual = bos.toString().trim();
        assertEquals("-5,0,10", actual,
            "引数 「-5 0 10」 に対する出力結果が異なります。（引数を動的に処理していますか？）"
        );
    }
    
    /* 末尾の改行チェック
     * 出力の最後に改行が正しく入っているかを検証
    */
   @Test
    public void testOutputEndingWithNewline() {
        Prog41.main(new String[]{"3", "1", "2"});
        
        String output = bos.toString();
        assertTrue(output.endsWith(System.lineSeparator()),
            "出力の末尾に改行がありません。System.out.println を使用してください。"
        );
    }
}
