class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
         int n = items.length;
        int[] freq = new int[n + 1];
        int minPrice = Integer.MAX_VALUE;

        // 1. Count factor frequencies (item[0]) and find cheapest price (item[1])
        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        // 2. Sieve-like step to count multiples for each factor
        int[] multiplesCount = new int[n + 1];
        // Use Long for value because total pairs across all items can exceed 2 * 10^9
        TreeMap<Integer, Long> discountedOffers = new TreeMap<>();

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            // Buying 2 of the cheapest item is better or equal if price >= 2 * minPrice
            if ((long) price >= 2L * minPrice) {
                continue;
            }

            if (multiplesCount[factor] == 0) {
                for (int m = factor; m <= n; m += factor) {
                    multiplesCount[factor] += freq[m];
                }
            }

            // Exclude the item itself (i != j)
            long maxFreebies = multiplesCount[factor] - 1;
            if (maxFreebies > 0) {
                discountedOffers.merge(price, maxFreebies, Long::sum);
            }
        }

        // 3. Greedily buy the cheapest "2-for-1" pairs first
        long totalItems = 0;
        long remainingBudget = budget;

        for (Map.Entry<Integer, Long> entry : discountedOffers.entrySet()) {
            long price = entry.getKey();
            long availablePairs = entry.getValue();

            if (remainingBudget < price) {
                break;
            }

            long canBuy = Math.min(availablePairs, remainingBudget / price);
            remainingBudget -= canBuy * price;
            totalItems += canBuy * 2;
        }

        // 4. Spend remaining budget on the single cheapest item
        totalItems += remainingBudget / minPrice;

        return (int) totalItems;
    }
}