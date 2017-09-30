(ns euler.p015)

(def num-paths
  (memoize (fn [x y]
             (if (or (zero? x) (zero? y))
               1
               (+ (#'num-paths (dec x) y)
                  (#'num-paths x (dec y)))))))

(defn solution {:answer 137846528820} []
  (num-paths 20 20))
