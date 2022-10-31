package ru.croc.task6;

public class AnnotatedImage {
    private final String imagePath;

    private final Annotation[] annotations;

    public AnnotatedImage(String imagePath, Annotation... annotations) {
        this.imagePath = imagePath;
        this.annotations = annotations;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public Annotation[] getAnnotations() {
        return this.annotations;
    }

    public Annotation findByLabel(String label) throws ObjectNotFoundExc {
        for (Annotation annotation
                : annotations) {
            if (annotation.checkIfContainsLabel(label)) return annotation;
        }
        throw new ObjectNotFoundExc("Nothing was found");
    }

    public Annotation findByPoint(double x,
                                  double y) throws ObjectNotFoundExc {
        for (Annotation annotation
                : annotations) {
            if (annotation.checkIfContainsPoint(x, y)) return annotation;
        }
        throw new ObjectNotFoundExc("Nothing was found");
    }

}
