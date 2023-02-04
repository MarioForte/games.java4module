import os
from PIL import Image, ImageDraw, ImageFont

current_dir = os.getcwd()

def generate_card(suit, rank):
    # Create a blank image
    card = Image.new("RGBA", (72, 97), (255, 255, 255, 255))
    draw = ImageDraw.Draw(card)

    # Add suit symbol
    if suit == '♣':
        symbol = "clubs.png"
        outline = 0
    elif suit == '♦':
        symbol = "diamonds.png"
        outline = 255
    elif suit == '♠':
        symbol = "spades.png"
        outline = 0
    elif suit == '♥':
        symbol = "hearts.png"
        outline = 255

    suit_symbol = Image.open(f"{current_dir}/{symbol}")

    font = ImageFont.truetype("arial.ttf", 20)

    card.paste(suit_symbol, (7, 7), suit_symbol)
    draw.text((25, 5), rank, font=font, fill=(0, 0, 0, 255))

    card  = card.rotate(180)
    draw = ImageDraw.Draw(card)

    card.paste(suit_symbol, (7, 7), suit_symbol)
    draw.text((25, 5), rank, font=font, fill=(0, 0, 0, 255))

    # Add a border
    draw.rectangle([0, 0, 71, 96], outline=(outline, 0, 0, 255))
    
    return card


suits = ["♣", "♠", "♥", "♦"]
ranks = ["2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"]

i = 0

# Generate all cards and save them as separate image files
for rank in ranks:
    for suit in suits:
        i += 1
        card = generate_card(suit, rank)
        try:
            card.save(f"{current_dir}/cards/k{i}.png")
        except FileNotFoundError:
            os.mkdir("cards")
            card.save(f"{current_dir}/cards/k{i}.png")
