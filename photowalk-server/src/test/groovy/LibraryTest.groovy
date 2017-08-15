import de.photowalk.data.Location
import spock.lang.Specification

class LibraryTest extends Specification {
    def "someLibraryMethod returns true"() {
        setup:
        def lib = new Location()

        when:
        def result = lib

        then:

        result
    }
}