(ns euler.p059
  (:use euler.numeric))

(def lower-case (range (int \a) (inc (int \z))))

(defn solution {:answer 107359} []
  (let [encrypted (map #(Integer/parseInt %)  (.split (.trim (slurp "data/cipher1.txt")) ","))]
    (sum (map int
              (first
               (filter #(re-find #"\sthe\s.*\sthe\s" %)
                       (for [c1 lower-case, c2 lower-case, c3 lower-case]
                         (apply str (map #(char (bit-xor %1 %2)) encrypted (cycle [c1 c2 c3]))))))))))
