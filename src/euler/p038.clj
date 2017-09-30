(ns euler.p038
  (:use euler.numeric))

(defn- concat-product [n]
  (number (take 9 (mapcat #(str (* n %)) (iterate inc 1)))))

(defn- pandigital-concat [n ds]
  (let [prod (concat-product n)]
    (when (= (into #{} (digits prod)) ds)
      prod)))

(defn solution {:answer 932718654} []
  (let [ds (into #{} (range 1 10))]
    (first
     (filter identity
      (map #(pandigital-concat % ds)
           (range 9999 1 -1))))))
