from flask import Flask
from flask import jsonify
from flask_restful import reqparse
from flask import url_for

app = Flask(__name__)
database = []
Pdatabase = []
manus = {'lattle': 4.0, 'macchiato': 5.35, 'cappuccino':7.5 , 'mocha':8.0} #type
others = {'hot':0.5,'shot':1.0, 'none':0.0} #addtions


class Order:
    def __init__(self, OID, type, cost, payment, additions = 'none', status = 'open', Pstatus = 'unpaid'):
        self.OID = OID
        self.type = type
        self.cost = cost
        self.payment = payment
        self.additions = additions
        self.status = status #flag to check if can be updated (open,processing,canceled,ready)
        self.Pstatus = Pstatus #flag to check payment states

class Payment:
    def __init__(self, PID, Ptype, amount, cardNo = '0', expireDate = 'none', name = 'none'):
        self.PID = PID
        self.Ptype = Ptype
        self.amount = amount
        self.cardNo = cardNo
        self.expireDate = expireDate
        self.name = name


#Order Creation
@app.route("/orders", methods=['POST'])
def add_order():
    parser = reqparse.RequestParser()
    parser.add_argument('OID', type=str, required=True, help = 'Required attribute input error')
    parser.add_argument('type', type=str, required=True, help = 'Required attribute input error')
    parser.add_argument('additions', type=str, help = 'Input error')
    args = parser.parse_args()

    global OID
    OID = args.get("OID")
    type = args.get("type")
    additions = args.get("additions")
    if type not in manus: #contraint the input of type
        return jsonify(Input = False), 404
    cost = manus[type] #connect to the dict to know the price
    payment = url_for('add_payment', PID = OID)

    if not additions:
        additions = 'none' #avoid null
    else:
        if additions not in others: #contraint the input of additions
            return jsonify(Input = False), 404
        cost += others[additions] #calculate the total price

    database.append(Order(OID, type, cost, payment, additions, status='open', Pstatus='unpaid'))
    return jsonify(OrderID = OID, TotalCost = cost, PaymentLink = payment),201


#Order Retrieving (orders list matching the specific states such as open orders)
@app.route("/orders-matching/<status>", methods=['GET'])
def get_orders(status):
    response = jsonify([od.__dict__ for od in database if od.status == status]), 200
    return response


#Order Retrieving (single order identified by OID)
@app.route("/orders/<OID>", methods=['GET'])
def get_order(OID):
    for od in database:
        if od.OID == OID:
           return jsonify(od.__dict__), 200
    return jsonify(ID=False), 404


#Order Retrirving (orders list)
@app.route("/orders", methods=['GET'])
def get_orderss():
    response = jsonify([od.__dict__ for od in database]), 200
    return response


#Order Updating - by cashier
@app.route("/orders/<OID>", methods=['PUT'])
def update_order(OID):
    parser = reqparse.RequestParser()
    parser.add_argument('type', type=str, help = 'Input error')
    parser.add_argument('additions', type=str, help = 'Input error')
    args = parser.parse_args()

    type = args.get("type")
    additions = args.get("additions")
    if type not in manus: #contraint the input of type
        return jsonify(Input = False), 404
    if additions not in others:  # contraint the input of additions
        return jsonify(Input = False), 404
    cost = manus[type] + others[additions] #new total price
    payment = url_for('add_payment',PID = OID)

    for od in database:
        if od.OID == OID and od.status == 'open' and od.Pstatus == 'unpaid':
            database.remove(od)
            database.append(Order(OID, type, cost, payment, additions, status = 'open', Pstatus = 'unpaid'))
            return jsonify(OrderID=OID), 200
    return jsonify(OrderID=False), 404


#Order Upadating of status - by barista
@app.route("/orders/<OID>", methods=['PATCH'])
def update_status(OID):
    for od in database:
        if od.OID == OID:
            od.status = 'processing' #no more updating allowed for this drink
            return jsonify(OrderID=OID), 200
    return jsonify(OrderID=False), 404


#Order Cancelling - before payment
@app.route("/orders/<OID>", methods=['DELETE'])
def cancel_order(OID):
    for od in database:
        if od.OID == OID and od.Pstatus == 'unpaid': #cannot cancel after payment
            od.status = 'canceled' #mark the status as cancelled
            return jsonify(OrderID=OID), 200
    return jsonify(OrderID=False), 404

#Payment Creation - by updating - idempotent
@app.route("/payments/<PID>", methods=['PUT'])
def add_payment(PID):
    parser = reqparse.RequestParser()
    parser.add_argument('Ptype', type=str, required = True, help = 'Required attribute input error')
    parser.add_argument('amount', type=float, required = True, help = 'Required attribute input error')
    parser.add_argument('cardNo', type=int, help = 'Input error')
    parser.add_argument('expireDate', type=str, help = 'Input error')
    parser.add_argument('name', type=str, help = 'Input error')
    args = parser.parse_args()

    Ptype = args.get("Ptype") #cash or card
    amount = args.get("amount")
    cardNo = args.get("cardNo")
    expireDate = args.get("expireDate")
    name = args.get("name")

    if Ptype == 'cash':
        cardNo = 0
        expireDate= 'none'
        name = 'none'

    for od in database:
        if od.OID == PID:
            od.Pstatus = 'paid' #mark as paid

    Pdatabase.append(Payment(PID, Ptype, amount, cardNo, expireDate, name))
    for i in Pdatabase:
        if i.PID == PID:
            return jsonify(i.__dict__),200 #Payment= PID


#Payment Details retriving (identified by PID)
@app.route("/payments/<PID>", methods=['GET'])
def get_payment(PID):
    for p in Pdatabase:
        if p.PID == PID:
            for i in database:
                if i.OID == PID:
                    i.status = 'ready' #after checking, the drink is ready for releasing
            return jsonify(p.__dict__), 200
    return jsonify(Payment=False), 404   #not paid yet - cannot release


if __name__ == "__main__":
    app.run()
