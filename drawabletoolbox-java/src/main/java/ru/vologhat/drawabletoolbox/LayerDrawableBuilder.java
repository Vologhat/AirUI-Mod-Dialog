package ru.vologhat.drawabletoolbox;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.view.Gravity;

import java.util.ArrayList;
import java.util.List;

class LayerDrawableBuilder {
    private static final int DIMEN_UNDEFINED = Integer.MIN_VALUE;

    private int paddingMode = LayerDrawable.PADDING_MODE_NEST;
    private int paddingLeft = 0;
    private int paddingTop = 0;
    private int paddingRight = 0;
    private int paddingBottom = 0;
    private int paddingStart = 0;
    private int paddingEnd = 0;
    private ArrayList<Layer> layers = new ArrayList<>();


    public LayerDrawableBuilder setPaddingMode(int padding) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.paddingMode = padding;
        }
        return this;
    }

    public LayerDrawableBuilder setPaddingLeft(int padding) {
        this.paddingLeft = padding;
        return this;
    }

    public LayerDrawableBuilder setPaddingTop(int padding) {
        this.paddingTop = padding;
        return this;
    }

    public LayerDrawableBuilder setPaddingRight(int padding) {
        this.paddingRight = padding;
        return this;
    }

    public LayerDrawableBuilder setPaddingBottom(int padding) {
        this.paddingBottom = padding;
        return this;
    }

    public LayerDrawableBuilder setPaddingStart(int padding) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.paddingStart = padding;
        }
        return this;
    }

    public LayerDrawableBuilder setPaddingEnd(int padding) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.paddingEnd = padding;
        }
        return this;
    }

    public LayerDrawableBuilder setPaddingRelative(int padding) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setPaddingTop(padding);
            setPaddingStart(padding);
            setPaddingBottom(padding);
            setPaddingEnd(padding);
        }
        return this;
    }

    public LayerDrawableBuilder add(Drawable drawable) {
        layers.add(new Layer(drawable));
        return this;
    }

    public LayerDrawableBuilder setInsetLeft(int inset) {
        layers.get(layers.size()-1).insetLeft = inset;
        return this;
    }

    public LayerDrawableBuilder setInsetTop(int inset) {
        layers.get(layers.size()-1).insetTop = inset;
        return this;
    }

    public LayerDrawableBuilder setInsetRight(int inset) {
        layers.get(layers.size()-1).insetRight = inset;
        return this;
    }

    public LayerDrawableBuilder setInsetBottom(int inset) {
        layers.get(layers.size()-1).insetBottom = inset;
        return this;
    }

    public LayerDrawableBuilder setInsetStart(int inset) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            layers.get(layers.size() - 1).insetStart = inset;
        }
        return this;
    }

    public LayerDrawableBuilder setInsetEnd(int inset) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            layers.get(layers.size() - 1).insetEnd = inset;
        }
        return this;
    }

    public LayerDrawableBuilder setInsetRelative(int inset) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Layer layer = layers.get(layers.size() - 1);
            layer.insetStart = inset;
            layer.insetEnd = inset;
            layer.insetTop = inset;
            layer.insetBottom = inset;
        }
        return this;
    }

    public LayerDrawableBuilder setInsetRelative(int insetStart, int insetTop, int insetEnd, int insetBottom) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Layer layer = layers.get(layers.size() - 1);
            layer.insetStart = insetStart;
            layer.insetEnd = insetEnd;
            layer.insetTop = insetTop;
            layer.insetBottom = insetBottom;
        }
        return this;
    }

    public LayerDrawableBuilder setGravity(int gravity) {
        layers.get(layers.size() - 1).gravity = gravity;
        return this;
    }

    public LayerDrawableBuilder modify(int index, Drawable drawable,
                       int width, int height,
                       int insetLeft, int insetTop,
                       int insetRight, int insetBottom,
                       int insetStart, int insetEnd) {
        Layer layer = layers.get(0);
        layer.drawable = drawable;
        if (width != DIMEN_UNDEFINED) layer.width = width;
        if (height != DIMEN_UNDEFINED) layer.height = height;
        if (insetLeft != DIMEN_UNDEFINED) layer.insetLeft = insetLeft;
        if (insetTop != DIMEN_UNDEFINED) layer.insetTop = insetTop;
        if (insetRight != DIMEN_UNDEFINED) layer.insetRight = width;
        if (insetBottom != DIMEN_UNDEFINED) layer.insetBottom = width;
        if (insetStart != DIMEN_UNDEFINED) layer.insetStart = width;
        if (insetEnd != DIMEN_UNDEFINED) layer.insetEnd = width;
        return this;
    }

    LayerDrawable build() {
        LayerDrawable layerDrawable = null;
        Drawable[] drawables = new Drawable[layers.size()];
        boolean b = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
        for (int i = 0; i < layers.size(); ++i) {
            drawables[i] = layers.get(i).drawable;
            if (drawables.length == layers.size()) {
                if (i == layers.size() - 1) {
                    i = 0;
                    layerDrawable = new LayerDrawable(drawables);
                }
                Layer layer = layers.get(i);
                layerDrawable.setLayerInset(i, layer.insetLeft, layer.insetTop, layer.insetRight, layer.insetBottom);

                if (b) {
                    if (layer.insetStart != DIMEN_UNDEFINED || layer.insetEnd != DIMEN_UNDEFINED) {
                        layerDrawable.setLayerInsetRelative(i, layer.insetStart, layer.insetTop, layer.insetLeft, layer.insetBottom);
                    }
                }
                layerDrawable.setId(i, i);
                if (b) {
                    layerDrawable.setLayerGravity(i, layer.gravity);
                    layerDrawable.setLayerInsetStart(i, layer.insetStart);
                    layerDrawable.setLayerInsetEnd(i, layer.insetEnd);
                }
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            layerDrawable.setPaddingMode(paddingMode);
        }
        if (b) {
            layerDrawable.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
            if (paddingStart != DIMEN_UNDEFINED || paddingEnd != DIMEN_UNDEFINED) {
                layerDrawable.setPaddingRelative(paddingStart, paddingTop, paddingEnd, paddingBottom);
            }
        }
        return layerDrawable;
    }

    class Layer {
        private Drawable drawable;
        private int gravity = Gravity.NO_GRAVITY;
        private int width = -1;
        private int height = -1;
        private int insetLeft = 0;
        private int insetTop = 0;
        private int insetRight = 0;
        private int insetBottom = 0;
        private int insetStart = DIMEN_UNDEFINED;
        private int insetEnd = DIMEN_UNDEFINED;

        public Layer(Drawable drawable) {
            this.drawable = drawable;
        }

        Drawable getDrawable() {
            return drawable;
        }
    }
}
