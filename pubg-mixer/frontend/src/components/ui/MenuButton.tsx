interface Props {
  text: string
  onClick?: () => void
  variant?: "primary" | "secondary"
}

export default function MenuButton({ text, onClick, variant = "primary" }: Props) {
  const variantClassName =
    variant === "secondary"
      ? "bg-black/40 text-slate-100 border border-white/20 hover:bg-black/60"
      : "bg-emerald-500/80 text-black font-bold shadow-[0_0_25px_rgba(16,185,129,0.7)] hover:bg-emerald-400"

  return (
    <button
      onClick={onClick}
      className={[
        "w-full",
        "h-12",
        "rounded-full",
        "font-semibold",
        "transition",
        "duration-200",
        "focus:outline-none",
        "focus:ring-2",
        "focus:ring-emerald-400/60",
        "disabled:opacity-60",
        "disabled:cursor-not-allowed",
        variantClassName,
      ].join(" ")}
    >
      {text}
    </button>
  )
}