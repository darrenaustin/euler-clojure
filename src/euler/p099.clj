(ns euler.p099)

(defn solution {:answer 709} []
  (let [pairs (partition 2 (map read-string (re-seq #"\d+" (slurp "data/base_exp.txt"))))]
    ;; Rather than test a full b^e expansion, we should just be able to compare
    ;; e * log(b) to determine which result will be largest.  Also need to keep
    ;; track of the line number.
    (let [[line value]
          (reduce (fn [[max-line max-value] [line value]]
                    (if (> value max-value)
                      [line value]
                      [max-line max-value]))
                  (map-indexed (fn [line [base exp]] [line (* exp (Math/log base))]) pairs))]
      (inc line))))
