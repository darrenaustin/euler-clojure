(ns dma.euler.p031)

(def ways
  (memoize (fn [n coins]
             (if (seq coins)
               (let [coin (first coins)]
                 (cond (> coin n) 0
                       (= coin n) 1
                       :else (+ (#'ways (- n coin) coins)
                                (#'ways n (rest coins)))))
               0))))

(defn solution {:answer 73682} []
  (ways 200 [1 2 5 10 20 50 100 200]))
