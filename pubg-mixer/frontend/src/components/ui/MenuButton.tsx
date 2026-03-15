interface Props {
  text: string
  onClick?: () => void
}

export default function MenuButton({ text, onClick }: Props) {
  return (
    <button
      onClick={onClick}
      className="
        w-full
        h-12
        rounded-lg
        bg-blue-600
        text-white
        font-semibold
        transition
        hover:bg-blue-700
      "
    >
      {text}
    </button>
  )
}