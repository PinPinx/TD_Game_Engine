// This entire file is part of my masterpiece.
// Thomas Puglisi

package engine.shop;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import javafx.event.EventHandler;
import javafx.scene.Node;
import engine.fieldsetting.Settable;
import engine.game.Player;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.gameobject.Purchasable;
import engine.gameobject.weapon.upgradetree.upgradebundle.UpgradeBundle;
import gameworld.StructurePlacementException;


/**
 * Manages the maps containing the purchasable prototypes.
 *
 * @author Tom Puglisi
 *
 */
public class ShopModelSimple implements ShopModel {
    private Player currentPlayer;
    private ShopWorld currentWorld;
    private double markup;
    private Map<String, Purchasable<GameObject>> purchasableMap;
    private Map<String, UpgradeBundle> upgradeMap;

    private GameObject currentGameObject;

    public ShopModelSimple () {
        markup = 1;
        currentPlayer = new Player();
    }

    public ShopModelSimple (ShopWorld currentWorld, Player currentPlayer, double markup) {
        this.markup = markup;
        this.currentWorld = currentWorld;
        this.currentPlayer = currentPlayer;
        purchasableMap = new HashMap<>();
        upgradeMap = new HashMap<>();
    }

    public ShopModelSimple (List<Purchasable<GameObject>> purchasables,
                            ShopWorld currentWorld, Player currentPlayer, double markup) {
        this(currentWorld, currentPlayer, markup);
        setPurchasables(purchasables);
    }

    @Settable
    public void setMarkup (double markup) {
        this.markup = markup;
    }

    @Settable
    @Override
    public void setShopWorld (ShopWorld world) {
        currentWorld = world;
    }

    @Settable
    public void setPlayer (Player player) {
        currentPlayer = player;
    }

    private void addPurchasable (Purchasable<GameObject> purchasable) {
        purchasableMap.put(purchasable.getName(), purchasable);
    }

    @Settable
    public void setPurchasables (List<Purchasable<GameObject>> purchasables) {
        purchasableMap.clear();
        purchasables.forEach(purchasable -> addPurchasable(purchasable));
    }

    @Override
    public List<ItemGraphic> getItemGraphics () {
        List<ItemGraphic> items = new ArrayList<ItemGraphic>();
        purchasableMap.values().forEach(purchasable -> items.add(new ItemGraphic(purchasable
                .getName(), purchasable.getShopGraphic())));
        return items;
    }

    @Override
    public List<UpgradeGraphic> getUpgradeGraphics (GameObject gameObject) {
        currentGameObject = gameObject;
        List<UpgradeBundle> bundles = gameObject.getWeapon().getNextUpgrades();
        List<UpgradeGraphic> upgradeGraphics = new ArrayList<>();
        upgradeMap.clear();
        bundles.forEach(bundle -> {
            String name = bundle.getName();
            upgradeMap.put(name, bundle);
            ItemGraphic graphic = new ItemGraphic(name, bundle.getShopGraphic());
            graphic.unGlow();
            upgradeGraphics.add(new UpgradeGraphic(graphic, canPurchase(name), bundle
                    .isFinalUpgrade()));
        });
        return upgradeGraphics;
    }

    /**
     * Purchases an item and places it at the selected position on the screen
     *
     * @param name Name of GameObject
     * @param location Location to be placed
     */
    public boolean purchaseGameObject (String name, PointSimple location, EventHandler selected) {
        if (canPurchase(name) && checkPlacement(name, location)) {
            try {
                GameObject tower = purchasableMap.get(name).clone();
                // GameObject tower = new TestTower(1, 100, 100);
                tower.getGraphic().getNode().setOnMousePressed(selected);
                currentWorld.addObject(tower, location);
                currentPlayer.getWallet().withdraw(getPrice(name));
                return true;
            }
            catch (StructurePlacementException e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public void sellGameObject (GameObject obj) {
        currentPlayer.getWallet().deposit(obj.getValue());
        currentWorld.removeObject(obj);
    }

    /**
     * Applies an upgrade to the given GameObject and subtracts the appropriate amount from the
     * player's wallet.
     *
     * @param itemGraphic
     */
    @Override
    public void purchaseUpgrade (String name, Consumer<GameObject> refreshUpgrades) {
        if (canPurchase(name)) {
            currentPlayer.getWallet().withdraw(getPrice(name));
            currentGameObject.getWeapon().applyUpgrades(upgradeMap.get(name));
            refreshUpgrades.accept(currentGameObject);
        }
    }

    @Override
    public boolean canPurchase (String name) {
        return currentPlayer.getWallet().getBalance() >= getPrice(name);
    }

    /**
     * 
     * @param name
     * @return the marked up price from the purchasable's intrinsic value
     */
    private double getPrice (String name) {
        return getPurchasable(name).getValue() * markup;
    }

    @Override
    public RangeDisplay getRangeDisplay (String name) {
        return ((GameObject) purchasableMap.get(name)).getRangeDisplay();
    }

    @Override
    public Map<ItemInfo, String> getInfo (String name) {
        Map<ItemInfo, String> info = new EnumMap<ItemInfo, String>(ItemInfo.class);
        info.put(ItemInfo.NAME, name);
        info.put(ItemInfo.DESCRIPTION, getPurchasable(name).getDescription());
        info.put(ItemInfo.PRICE, Double.toString(getPrice(name)));
        return info;
    }

    /**
     * Assumes that the given object exists either in the purchasableMap or the upgradeMap
     * 
     * @param name
     * @return the purchasable associated with the String name
     */
    private Purchasable<?> getPurchasable (String name) {
        if (purchasableMap.containsKey(name)) {
            return (Purchasable<?>) purchasableMap.get(name);
        }
        else {
            return (Purchasable<?>) upgradeMap.get(name);
        }
    }

    /**
     * 
     * Enum used by the view to determine what information to display
     *
     */
    public enum ItemInfo {
        NAME, DESCRIPTION, PRICE
    }

    @Override
    public boolean checkPlacement (String name, PointSimple location) {
        return currentWorld.isPlaceable(((GameObject) purchasableMap.get(name)).getGraphic()
                .getNode(), location);
    }

    @Override
    public GameObject getObjectFromNode (Node node) {
        return currentWorld.getObjectFromNode(node);
    }

}
