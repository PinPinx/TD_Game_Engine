package engine.shop;

public class UpgradeGraphic {

    private ItemGraphic graphic;
    private boolean canAfford;
    private boolean isFinal;

    public UpgradeGraphic (ItemGraphic graphic, boolean canAfford, boolean isFinal) {
        this.graphic = graphic;
        this.canAfford = canAfford;
        this.isFinal = isFinal;
    }

    public ItemGraphic getGraphic () {
        return graphic;
    }

    public boolean canAfford () {
        return canAfford;
    }

    public boolean isFinal () {
        return isFinal;
    }
}
