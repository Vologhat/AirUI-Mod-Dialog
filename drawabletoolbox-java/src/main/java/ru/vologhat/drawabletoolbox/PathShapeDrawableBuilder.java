package ru.vologhat.drawabletoolbox;

import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;

class PathShapeDrawableBuilder {
    private Path path = null;
    private float pathStandardWidth = 100f;
    private float pathStandardHeight = 100f;
    private int width = -1;
    private int height = -1;

    public PathShapeDrawableBuilder setPath(Path path, float pathStandardWidth, float pathStandardHeight) {
        this.path = path;
        this.pathStandardWidth = pathStandardWidth;
        this.pathStandardHeight = pathStandardHeight;
        return this;
    }

    public PathShapeDrawableBuilder setWidth(int width) {
        this.width = width;
        return this;
    }

    public PathShapeDrawableBuilder setHeight(int height) {
        this.height = height;
        return this;
    }

    public PathShapeDrawableBuilder setSize(int width, int height) {
        return this.setWidth(width).setHeight(height);
    }

    ShapeDrawable build(ShapeDrawable custom) {
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        if (path == null || width <= 0 || height <= 0) {
            return shapeDrawable;
        }
        PathShape pathShape = new PathShape(path, pathStandardWidth, pathStandardHeight);
        shapeDrawable.setShape(pathShape);
        shapeDrawable.setIntrinsicWidth(width);
        shapeDrawable.setIntrinsicHeight(height);
        if (custom != null) {
            shapeDrawable = null;
        }
        return shapeDrawable;
    }
}
