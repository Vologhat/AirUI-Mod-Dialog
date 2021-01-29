package ru.vologhat.drawabletoolbox;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.StateSet;

class StateListDrawableBuilder {
    private Drawable pressed = null;
    private Drawable disabled = null;
    private Drawable selected = null;
    private Drawable normal = new ColorDrawable(Color.TRANSPARENT);

    public StateListDrawableBuilder setPressed(Drawable pressed) {
        this.pressed = pressed;
        return this;
    }

    public StateListDrawableBuilder setDisabled(Drawable disable) {
        this.disabled = disable;
        return this;
    }

    public StateListDrawableBuilder setSelected(Drawable selected) {
        this.selected = selected;
        return this;
    }

    public StateListDrawableBuilder setNormal(Drawable normal) {
        this.normal = normal;
        return this;
    }

    public Drawable build() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        setupStateListDrawable(stateListDrawable);
        return stateListDrawable;
    }

    private void setupStateListDrawable(StateListDrawable stateListDrawable) {
        if (pressed != null) {
            stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, this.pressed);
        }
        if (disabled != null) {
            stateListDrawable.addState(new int[]{android.R.attr.state_enabled}, this.disabled);
        }
        if (selected != null) {
            stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, this.selected);
        }
        stateListDrawable.addState(StateSet.WILD_CARD, normal);
    }
}
