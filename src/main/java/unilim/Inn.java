package unilim;

import java.util.ArrayList;
import java.util.List;

public class Inn {
	private List<Item> items;

	public Inn() {
		items = new ArrayList<Item>();
		items.add(new Item("+5 Dexterity Vest", 10, 20));
		items.add(new Item("Aged Brie", 2, 0));
		items.add(new Item("Elixir of the Mongoose", 5, 7));
		items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
		items.add(new Item("Conjured Mana Cake", 3, 6));
	}

	public List<Item> getItems() {
		return items;
	}

	public void updateQuality() {
		for (int i = 0; i < items.size(); i++) {
			if (estAgedBrieOuBackstage(i)) {
				if (items.get(i).getQuality() < 50) {
					
					items.get(i).setQuality(items.get(i).getQuality() + 1);
					if (estBackstage(i)) {
						if (items.get(i).getSellIn() < 11) {
								items.get(i).setQuality(items.get(i).getQuality() + 1);
						}
					}
				}
			} else {
				if (items.get(i).getQuality() > 0) {
					if (nestPasSullfura(i)) {
						items.get(i).setQuality(items.get(i).getQuality() - 1);
					}
				}
			}

			if (nestPasSullfura(i)) {
				items.get(i).setSellIn(items.get(i).getSellIn() - 1);
			}

			if (items.get(i).getSellIn() < 0) {
				if (!items.get(i).getName().equals("Aged Brie")) {
					if (!estBackstage(i)) {
						if (items.get(i).getQuality() > 0) {
							if (nestPasSullfura(i)) {
								items.get(i).setQuality(items.get(i).getQuality() - 1);
							}
						}
					} else {
						items.get(i).setQuality(items.get(i).getQuality() - items.get(i).getQuality());
					}
				} else {
					if (items.get(i).getQuality() < 50) {
						items.get(i).setQuality(items.get(i).getQuality() + 1);
					}
				}
			}
		}

	}

	private boolean nestPasSullfura(int i) {
		return !items.get(i).getName().equals("Sulfuras, Hand of Ragnaros");
	}

	private boolean estBackstage(int i) {
		return items.get(i).getName().equals("Backstage passes to a TAFKAL80ETC concert");
	}

	private boolean estAgedBrieOuBackstage(int i) {
		return items.get(i).getName().equals("Aged Brie")
				|| estBackstage(i);
	}

}
