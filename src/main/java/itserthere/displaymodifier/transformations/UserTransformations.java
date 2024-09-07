package itserthere.displaymodifier.transformations;
import itserthere.displaymodifier.util.TransformationData;
import java.util.List;
import java.util.Objects;

public class UserTransformations {
    private final List<TransformationData> transformations;

    public UserTransformations(List<TransformationData> initialList) {
        this.transformations = initialList;
    }

    public List<TransformationData> userTransformations() {
        return transformations;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (UserTransformations) obj;
        return Objects.equals(this.transformations, that.transformations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transformations);
    }

    @Override
    public String toString() {
        return "UserTransformations[" +
                "transformations=" + transformations + ']';
    }
}
