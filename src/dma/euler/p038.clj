(ns dma.euler.p038
  (:use dma.euler.numeric))

(defn choose
  ([set n] (choose set n []))
  ([set n chosen]
     (if (zero? n)
       [chosen]
       (mapcat #(choose (disj set %) (dec n) (conj chosen %)) set))))

(defn concat-product [n]
  (number (take 9 (mapcat #(str (* n %)) (iterate inc 1)))))

(defn pandigital-concat [n ds]
  (let [prod (concat-product n)]
    (when (= (into #{} (digits prod)) ds)
      prod)))

(defn solution {:answer 932718654} []
  (let [digits (into #{} (range 1 10))]
    (first
     (filter identity
      (map #(pandigital-concat (number %) digits)
           (reverse (choose d 4)))))))
