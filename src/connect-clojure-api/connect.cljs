(ns connect-clojure-api.connect)

(defn connect [adapter coder]
  (let [encode (.encode coder)
        decode (.decode coder)
        connected false
        disconnected true
        id (atom 0)
        state (atom disconnected)
        commands (atom {})]

    (.onOpen adapter (reset! state connected))
    (.onEnd adapter (reset! state disconnected))

    (fn [payloadType payload callback]
      (if (= @state connected)
        (let [clientMsgId (swap! id inc)
              message (encode clientMsgId payloadType payload)]
          (swap! commands assoc clientMsgId callback)
          (.send adapter message))
        (callback disconnected)))))
