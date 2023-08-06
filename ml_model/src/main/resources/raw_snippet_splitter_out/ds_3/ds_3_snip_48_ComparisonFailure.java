package raw_snippet_splitter_out.ds_3;

public class ds_3_snip_48_ComparisonFailure {
    /**
     * Constructs a comparison failure.
     * @param message the identifying message or null
     * @param expected the expected string value
     * @param actual the actual string value
    */
    public ComparisonFailure (String message, String expected, String actual) {
        super (message);
        fExpected= expected;
        fActual= actual;
    }
}
