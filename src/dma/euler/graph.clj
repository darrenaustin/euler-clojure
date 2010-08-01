(ns dma.euler.graph)

(defn path [node parents]
  (loop [p ()
         current node
         parent (parents current)]
    (if parent
      (recur (conj p current) parent (parents parent))
      (conj p current))))

(defn a* [graph start goal h neighbors movement-cost]
  (loop [open #{start}
         closed #{}
         costs {start (movement-cost graph start start)}
         parents {}
         ranks {}
         current nil
         visit nil]
    (if (seq visit)
      (let [n (first visit)
            cost (+ (costs current) (movement-cost graph current n))
            n-cost (if-let [nc (costs n)] nc 0)
            open (if (and (open n) (< cost n-cost)) (disj open n) open)
            closed (if (and (closed n) (< cost n-cost)) (disj closed n) closed)]
        (if (and (not (open n)) (not (closed n)))
          (recur (conj open n) closed (assoc costs n cost) (assoc parents n current)
                 (assoc ranks n (+ cost (h graph n goal))) current (rest visit))
          (recur open closed costs parents ranks current (rest visit))))
      (when-let [current (first (sort (fn [x y] (compare (ranks x) (ranks y))) open))]
        (if (= current goal)
          (path current parents)
          (recur (disj open current) (conj closed current) costs parents ranks current (neighbors graph current)))))))
