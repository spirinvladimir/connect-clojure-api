(ns connect-clojure-api.connect)

(defn connect [adapter codec]
  (let [encode (.encode codec)
        decode (.decode codec)
        connected false
        disconnected true
        id (atom 0)
        state (atom disconnected)
        callbacks (atom {})
        send (fn [payloadType payload callback]
               (if (= @state connected)
                 (let [clientMsgId (swap! id inc)
                       message (encode clientMsgId payloadType payload)]
                   (swap! callbacks assoc clientMsgId callback)
                   (.send adapter message))
                 (callback disconnected)))
        recive (fn [payloadType payload clientMsgId]
                 (let [callback (get @callbacks clientMsgId)]
                   (if nil? callback
                     nil
                     (callback payload))))]


    (.onOpen adapter (reset! state connected))
    (.onEnd adapter (reset! state disconnected))

    (.decode codec recive)
    send))
