(ns euler-clojure.core)

(defn abs [n]
  (if (neg? n) (- n) n))
