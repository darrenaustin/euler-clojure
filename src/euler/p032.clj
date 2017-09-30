(ns euler.p032
  (:use euler.numeric [clojure.math.combinatorics :as combo]))


;; TODO - build my own permutations function
(defn solution {:answer 45228} []
  (sum
   (into #{}
         (filter identity
                 (map (fn [ds]
                        (let [a (number (subvec ds 0 2))
                              b (number (subvec ds 2 5))
                              c (number (subvec ds 0 1))
                              d (number (subvec ds 1 5))
                              p (number (subvec ds 5))]
                          (if (or (= (* a b) p)
                                  (= (* c d) p))
                            p)))
                     (combo/permutations (range 1 10)))))))

